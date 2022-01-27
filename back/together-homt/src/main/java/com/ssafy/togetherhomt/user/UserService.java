package com.ssafy.togetherhomt.user;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.config.media.GlobalConfig;
import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.FeedDto;
import com.ssafy.togetherhomt.feed.media.Media;
import com.ssafy.togetherhomt.feed.media.MediaRepository;
import com.ssafy.togetherhomt.user.auth.LoginDto;
import com.ssafy.togetherhomt.user.info.SignupDto;
import com.ssafy.togetherhomt.user.info.UpdateDto;
import com.ssafy.togetherhomt.util.Mailing.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MailingService mailingService;

    @Autowired
    private GlobalConfig config;

    @Autowired
    private MediaRepository mediaRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MailingService mailingService,
                       GlobalConfig config, MediaRepository mediaRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailingService = mailingService;
        this.config = config;
        this.mediaRepository = mediaRepository;
    }

    @Transactional
    public String signup(SignupDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null)
            return "failure";


        User user = User.builder()
                .email(userDto.getEmail())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .username(userDto.getUsername())
                .role("ROLE_USER")
                .introduce("")
                .build();

        userRepository.save(user);

        return "success";
    }

    public SignupDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null)
            return null;

        if (!bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword()))
            return null;

        return new SignupDto(user.getEmail(), user.getPassword(), user.getUsername());
    }

    @Transactional
    public void withdraw(String email){
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
    }

    @Transactional
    public void passwordUpdate(String email,LoginDto loginDto){
        User user = userRepository.findByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(loginDto.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public String passwordFind(String email) throws Exception{
        System.out.println("email = " + email);
        User user = userRepository.findByEmail(email);
        String newPassword = mailingService.sendSimpleMessage(email);

        System.out.println("newPassword = " + newPassword);
        System.out.println("user = " + user);

        user.setPassword(bCryptPasswordEncoder.encode(newPassword));

        userRepository.save(user);

        return "success";
    }

    public User getProfile(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public String update(UpdateDto updateDto){

        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());

        MultipartFile multipartFile = updateDto.getMedia();

        if(multipartFile == null || multipartFile.isEmpty()){
            user.setUsername(updateDto.getUsername());
            user.setIntroduce(updateDto.getIntroduce());
            userRepository.save(user);
            return "success";

        }else{
            // 현재 날짜 폴더만들어서 저장
            String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
            String uploadFilePath = config.getUploadFilePath()+currentDate+"/";

            // 랜덤이름 + . 확장자 가져오기
            String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1,multipartFile.getOriginalFilename().length());
            String filename = UUID.randomUUID().toString()+"."+prefix;

            // 폴더 없으면 만들기
            File folder = new File(uploadFilePath);
            if(!folder.isDirectory()){
                folder.mkdirs();
            }
            String pathname = uploadFilePath+filename;
            String resourcePathname = config.getUploadResourcePath()+currentDate+"/"+filename;

            // url 경로 출력
            System.out.println("resourcePathname = " + resourcePathname);

            // 새로 파일 만들기
            File dest = new File(pathname);
            try {
                multipartFile.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 경로
            System.out.println("pathname = " + pathname);
            Media media = Media.builder()
                    .fileOriginName(multipartFile.getOriginalFilename())
                    .videoPath(uploadFilePath)
                    .videoUrl(pathname)
                    .build();
            mediaRepository.save(media);

            user.setProfile_url(resourcePathname);
            user.setUsername(updateDto.getUsername());
            user.setIntroduce(updateDto.getIntroduce());
            userRepository.save(user);

        }

        return "success";
    }
}

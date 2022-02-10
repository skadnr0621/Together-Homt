package com.ssafy.togetherhomt.user;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.config.media.GlobalConfig;
import com.ssafy.togetherhomt.user.info.SignupDto;
import com.ssafy.togetherhomt.user.info.UpdateDto;
import com.ssafy.togetherhomt.user.info.UserDto;
import com.ssafy.togetherhomt.util.Mailing.MailingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private GlobalConfig config;

    private CommonService commonService;
    private MailingService mailingService;

    private UserRepository userRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, GlobalConfig config, CommonService commonService, MailingService mailingService, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.config = config;
        this.commonService = commonService;
        this.mailingService = mailingService;
        this.userRepository = userRepository;
    }

    @Transactional
    public String signup(SignupDto signupDto) {
        if (userRepository.findByEmail(signupDto.getEmail()) != null)
            return "duplicate";

        User user = User.builder()
                .email(signupDto.getEmail())
                .password(bCryptPasswordEncoder.encode(signupDto.getPassword()))
                .username(signupDto.getUsername())
                .role("ROLE_USER")
                .introduce("")
                .build();
        userRepository.save(user);

        return "success";
    }

    @Transactional
    public void withdraw(){
        User user = commonService.getLoginUser();
        userRepository.delete(user);
    }

    @Transactional
    public void updatePassword(String newPassword){
        User user = userRepository.findByEmail(commonService.getLoginUser().getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
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

    public UserDto getProfile(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return null;
        else
            return new UserDto(user, true);
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

            user.setProfile_url(resourcePathname);
            user.setUsername(updateDto.getUsername());
            user.setIntroduce(updateDto.getIntroduce());
            userRepository.save(user);

        }

        return "success";
    }
}

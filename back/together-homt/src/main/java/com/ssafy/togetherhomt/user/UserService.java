package com.ssafy.togetherhomt.user;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.config.media.GlobalConfig;
import com.ssafy.togetherhomt.config.media.MediaService;
import com.ssafy.togetherhomt.feed.media.MediaRepository;
import com.ssafy.togetherhomt.user.follow.FollowRepository;
import com.ssafy.togetherhomt.user.info.SignupDto;
import com.ssafy.togetherhomt.util.Mailing.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
//@AllArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final CommonService commonService;
    private final MediaService mediaService;
    private final MailingService mailingService;

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, CommonService commonService, MediaService mediaService, MailingService mailingService, UserRepository userRepository, FollowRepository followRepository, MediaRepository mediaRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.commonService = commonService;
        this.mediaService = mediaService;
        this.mailingService = mailingService;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
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

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserDto> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(this.builder(user, false));
        }
        return userList;
    }

    public UserDto getProfile(User user) {
        return this.builder(user, true);
    }

    @Transactional
    public UserDto update(UserDto userDto, MultipartFile picture) {
        User user = userRepository.findByEmail(commonService.getLoginUser().getEmail());
        if (!user.getEmail().equals(userDto.getEmail()) || !user.getUsername().equals(userDto.getUsername())) {
            return null;
        }

        // update user
        if (picture != null) {
            String resourcePath = mediaService.save(picture);
            if (resourcePath == null)
                return null;
            else
                user.setPicturePath(resourcePath);
        }
        user.setIntroduce(userDto.getIntroduce());

        userRepository.save(user);
        return this.builder(user, true);
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


    // --------------------------------------------------

//    private List<UserDto> txFollowerListToUserDtoList(List<Follow> followerList) {
//        List<UserDto> followers = new ArrayList<>();
//        for (Follow follow : followerList) {
//            User follower = follow.getFollower();
//            followers.add(
//                    UserDto.builder()
//                            .email(follower.getEmail())
//                            .username(follower.getUsername())
//                            .profileUrl(follower.getProfile_url())
//                            .introduce(follower.getIntroduce())
//                            .build()
//            );
//        }
//        return followers;
//    }
//
//    private List<UserDto> txFollowingListToUserDtoList(List<Follow> followingList) {
//        List<UserDto> followings = new ArrayList<>();
//        for (Follow follow : followingList) {
//            User following = follow.getFollowing();
//            followings.add(
//                    UserDto.builder()
//                            .email(following.getEmail())
//                            .username(following.getUsername())
//                            .profileUrl(following.getProfile_url())
//                            .introduce(following.getIntroduce())
//                            .build()
//            );
//        }
//        return followings;
//    }

    // --------------------------------------------------

    private UserDto builder(User user, boolean verbose) {
        UserDto.UserDtoBuilder userDtoBuilder = UserDto.builder();
        userDtoBuilder
                .email(user.getEmail())
                .username(user.getUsername())
                .picturePath(user.getPicturePath())
                .introduce(user.getIntroduce());

        if (verbose) {
            userDtoBuilder
//                    .followers(txFollowerListToUserDtoList(user.getFollower()))
//                    .followings(txFollowingListToUserDtoList(user.getFollowing()))
                    .cntFollower(followRepository.countByFollower(user))
                    .cntFollowing(followRepository.countByFollowing(user));
        }

        return userDtoBuilder.build();
    }

}

package com.ssafy.togetherhomt.user.follow;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.info.UserDto;
import com.ssafy.togetherhomt.user.UserRepository;
import com.ssafy.togetherhomt.util.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@AllArgsConstructor
public class FollowController {

    private UserRepository userRepository;

    private FollowService followService;
    private CommonService commonService;


    // 나를 팔로우 하는 사람 검색
    @GetMapping("/{my-email}/followers")
    public ResponseEntity<?> listFollowers(@PathVariable("my-email") String myEmail) {
        User me = userRepository.findByEmail(myEmail);
        if (me == null)
            return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(followService.listFollowers(me), HttpStatus.OK);
    }

    // 내가 팔로우 하는 사람 검색
    @GetMapping("/{my-email}/followings")
    public ResponseEntity<?> listFollowings(@PathVariable("my-email") String myEmail) {
        User me = userRepository.findByEmail(myEmail);
        if (me == null)
            return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(followService.listFollowings(me), HttpStatus.OK);
    }

    @PostMapping("/{my-email}/{your-email}")
    public ResponseEntity<String> follow(@PathVariable("my-email") String myEmail,
                                         @PathVariable("your-email") String yourEmail) {

        PrincipalDetails principalDetails = (PrincipalDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User me = principalDetails.getUser();
        User you = userRepository.findByEmail(yourEmail);

        if (you == null || !myEmail.equals(me.getEmail()))
            return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);

        Follow follow = followService.follow(me, you);
        if (follow == null)
            return new ResponseEntity<>("Requested follow already exists", HttpStatus.CONFLICT);

        String msg = String.format("Follow success : %s ---> %s", me.getUsername(), you.getUsername());
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/{my-email}/{your-email}")
    public ResponseEntity<Void> unfollow(@PathVariable("my-email") String myEmail,
                                         @PathVariable("your-email") String yourEmail) {

        User me = userRepository.findByEmail(myEmail);
        User you = userRepository.findByEmail(yourEmail);

        if (you == null || !commonService.isLoginUser(me))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        followService.unfollow(me, you);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
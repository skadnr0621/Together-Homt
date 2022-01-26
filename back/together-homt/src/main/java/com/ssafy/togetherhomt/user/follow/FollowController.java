package com.ssafy.togetherhomt.user.follow;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api("팔로우 기능 접근 방법")
@RestController
@RequestMapping("/follow")
@AllArgsConstructor
public class FollowController {

    private UserRepository userRepository;

    private FollowService followService;
    private CommonService commonService;


    // 나를 팔로우 하는 사람 검색
    @ApiOperation(value = "팔로워 검색", notes = "나를 팔로우 하는 사람 검색")
    @GetMapping("/{my-email}/followers")
    public ResponseEntity<?> listFollowers(@PathVariable("my-email") String myEmail) {
        User me = userRepository.findByEmail(myEmail);
        if (me == null)
            return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(followService.listFollowers(me), HttpStatus.OK);
    }

    // 내가 팔로우 하는 사람 검색
    @ApiOperation(value = "팔로잉 검색", notes = "내가 팔로우 하는 사람 검색")
    @GetMapping("/{my-email}/followings")
    public ResponseEntity<?> listFollowings(@PathVariable("my-email") String myEmail) {
        User me = userRepository.findByEmail(myEmail);
        if (me == null)
            return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(followService.listFollowings(me), HttpStatus.OK);
    }

    @ApiOperation(value = "팔로우", notes = "로그인한 유저로 상대 유저 팔로우")
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

    @ApiOperation(value = "팔로우 취소", notes = "로그인한 유저로 상대 유저 팔로우 취소")
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
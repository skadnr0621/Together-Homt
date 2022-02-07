package com.ssafy.togetherhomt.lhy.user;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.lhy.follow.FollowRepository2;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class UserController2 {

    private CommonService commonService;
    private UserService2 userService2;

    private FollowRepository2 followRepository2;


    @GetMapping
    public ResponseEntity<?> getUser() {
        UserDto2 userDto2 = userService2.getUser();
        if (userDto2 == null)
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userDto2, HttpStatus.OK);
    }

}

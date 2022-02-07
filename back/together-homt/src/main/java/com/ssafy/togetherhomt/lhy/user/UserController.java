package com.ssafy.togetherhomt.lhy.user;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.lhy.follow.FollowRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=UTF-8")
@AllArgsConstructor
public class UserController {

    private CommonService commonService;
    private UserService userService;

    private FollowRepository followRepository;


    @GetMapping
    public ResponseEntity<?> getUser() {
        UserDto userDto = userService.getUser();
        if (userDto == null)
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}

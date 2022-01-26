package com.ssafy.togetherhomt.user;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.user.auth.LoginDto;
import com.ssafy.togetherhomt.user.info.SignupDto;
import com.ssafy.togetherhomt.user.info.UpdateDto;
import com.ssafy.togetherhomt.util.Mailing.MailingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("사용자(유저) 관련 기능 접근 방법")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private MailingService mailConfirmService;

    @Autowired
    public UserController(UserService userService, MailingService mailConfirmService) {
        this.userService = userService;
        this.mailConfirmService = mailConfirmService;
    }


    @ApiOperation(value = "회원 가입", notes = "회원 가입")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupDto signDto) {
        String result = userService.signup(signDto);

        if (result.equals("success"))
            return ResponseEntity.ok("success");
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "회원 가입 승인 이메일", notes = "승인 이메일 발송")
    @PostMapping({"/signup/confirm", "/reset-password"})
    public ResponseEntity<String> confirmMail(@RequestBody String email) throws Exception {
        return ResponseEntity.ok(mailConfirmService.sendSimpleMessage(email));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        System.out.println("UserController - login");
        SignupDto userDto = userService.login(loginDto);
        if (userDto != null)
            return ResponseEntity.ok("success");
        else
            System.out.println("fail");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴")
    @DeleteMapping("/{email}")
    public ResponseEntity<?> withdraw(@PathVariable String email){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null || !principalDetails.getUsername().equals(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.withdraw(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정")
    @PutMapping("/{email}")
    public ResponseEntity<?> update(@PathVariable String email, @Valid @RequestBody UpdateDto updateDto){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null || !principalDetails.getUsername().equals(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.update(email, updateDto);
        return ResponseEntity.ok("update success");
    }

    @ApiOperation(value = "비밀번호 초기화", notes = "비밀번호 찾기 후 생성된 난수로 비밀번호 초기화")
    @PutMapping("/{email}/passwordUpdate")
    public ResponseEntity<?> passwordUpdate(@PathVariable String email, @Valid @RequestBody LoginDto loginDto){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null || !principalDetails.getUsername().equals(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.passwordUpdate(email, loginDto);
        return ResponseEntity.ok("passwordUpdate success");
    }
}

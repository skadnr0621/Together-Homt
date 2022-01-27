package com.ssafy.togetherhomt.user;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.user.auth.LoginDto;
import com.ssafy.togetherhomt.user.info.SignupDto;
import com.ssafy.togetherhomt.user.info.UpdateDto;
import com.ssafy.togetherhomt.util.Mailing.MailingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(code = 500, message = "이미 존재하는 유저입니다. 회원가입에 실패하였습니다."),
            @ApiResponse(code = 200, message = "200 + \"success\". 회원가입에 성공하였습니다.")
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupDto signDto) {
        String result = userService.signup(signDto);

        if (result.equals("success"))
            return ResponseEntity.ok("success");
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "회원 가입 승인 이메일", notes = "승인 이메일 발송")
    @ApiResponses({
            @ApiResponse(code = 500, message = "존재하지 않는 계정입니다."),
            @ApiResponse(code = 200, message = "회원가입 인증 코드가 정상적으로 발송되었습니다.")
    })
    @PostMapping("/signup/confirm")
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

    @ApiOperation(value = "회원 정보 조회", notes = "회원 정보 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 정보 조회에 성공하였습니다.")
    })
    @GetMapping("/profile/{email}")
    public ResponseEntity<?> getProfile(@PathVariable String email) {
        User user = userService.getProfile(email);
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴")
    @ApiResponses({
            @ApiResponse(code = 400, message = "잘못된 요청입니다. 계정을 명시하지 않았거나 로그인 정보와 맞지 않습니다."),
            @ApiResponse(code = 200, message = "회원 탈퇴에 성공하였습니다.")
    })
    @DeleteMapping("/{email}")
    public ResponseEntity<?> withdraw(@PathVariable String email){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null || !principalDetails.getUsername().equals(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.withdraw(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "비밀번호 변경", notes = "비밀번호 찾기 후 생성된 난수로 비밀번호 초기화")
    @ApiResponses({
            @ApiResponse(code = 400, message = "잘못된 요청입니다. 계정을 명시하지 않았거나 로그인 정보와 맞지 않습니다."),
            @ApiResponse(code = 200, message = "비밀번호 재설정에 성공하였습니다.")
    })
    @PutMapping("/{email}/passwordUpdate")
    public ResponseEntity<?> passwordUpdate(@PathVariable String email, @Valid @RequestBody LoginDto loginDto){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null || !principalDetails.getUsername().equals(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.passwordUpdate(email, loginDto);
        return ResponseEntity.ok("passwordUpdate success");
    }

    @ApiOperation(value = "비밀번호 초기화", notes = "비밀번호 찾기 과정 중, 난수를 생성하여 비밀번호를 초기화하고, 메일로 전송합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "임시 재설정 비밀번호를 메일로 전송하는 데 성공하였습니다.")
    })
    @PostMapping("/passwordFind")
    public String passwordFind(@RequestBody String email) throws Exception{
        return userService.passwordFind(email);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정")
    @ApiResponses({
            @ApiResponse(code = 400, message = "잘못된 요청입니다. 계정을 명시하지 않았거나 로그인 정보와 맞지 않습니다. 또는 존재하지 않는 미디어 파일입니다."),
            @ApiResponse(code = 200, message = "회원 정보 수정에 성공하였습니다.")
    })
    @PutMapping("/profile/update")
    public String update(@Valid @RequestBody UpdateDto updateDto){
        return userService.update(updateDto);
    }

}

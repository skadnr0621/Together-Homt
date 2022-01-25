package com.ssafy.togetherhomt.user;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.user.auth.LoginDto;
import com.ssafy.togetherhomt.user.info.SignupDto;
import com.ssafy.togetherhomt.user.info.UpdateDto;
import com.ssafy.togetherhomt.util.Mailing.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupDto signDto) {
        String result = userService.signup(signDto);

        if (result.equals("success"))
            return ResponseEntity.ok("success");
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

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

    @DeleteMapping("/{email}")
    public ResponseEntity<?> withdraw(@PathVariable String email){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null || !principalDetails.getUsername().equals(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.withdraw(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> update(@PathVariable String email, @Valid @RequestBody UpdateDto updateDto){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null || !principalDetails.getUsername().equals(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.update(email, updateDto);
        return ResponseEntity.ok("update success");
    }

    @PutMapping("/{email}/passwordUpdate")
    public ResponseEntity<?> passwordUpdate(@PathVariable String email, @Valid @RequestBody LoginDto loginDto){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null || !principalDetails.getUsername().equals(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.passwordUpdate(email, loginDto);
        return ResponseEntity.ok("passwordUpdate success");
    }

    @PostMapping("/passwordFind")
    public String passwordFind(@RequestBody String email) throws Exception{
        return userService.passwordFind(email);
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<?> getProfile(@PathVariable String email) {
        User user = userService.getProfile(email);
        return ResponseEntity.ok(user);
    }
}

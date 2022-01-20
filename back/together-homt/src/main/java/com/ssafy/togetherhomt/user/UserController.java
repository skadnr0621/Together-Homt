package com.ssafy.togetherhomt.user;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.util.MailConfirm.MailConfirmService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private MailConfirmService mailConfirmService;

    @Autowired
    public UserController(UserService userService, MailConfirmService mailConfirmService) {
        this.userService = userService;
        this.mailConfirmService = mailConfirmService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDto userDto) {
        String result = userService.signup(userDto);

        if (result.equals("success"))
            return ResponseEntity.ok("success");
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/signup/mail-confirm")
    public ResponseEntity<String> confirmMail(@RequestBody String email) throws Exception {
        return ResponseEntity.ok(mailConfirmService.sendSimpleMessage(email));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        System.out.println("UserController - login");
        UserDto userDto = userService.login(loginDto);
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


    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return new ResponseEntity<String>("authorized", HttpStatus.OK);
    }

    @GetMapping("/main")
    public ResponseEntity<String> main() {
        return new ResponseEntity<String>("this is /user/main", HttpStatus.OK);
    }

}

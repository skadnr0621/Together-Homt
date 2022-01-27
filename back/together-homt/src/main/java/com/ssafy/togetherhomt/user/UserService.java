package com.ssafy.togetherhomt.user;

import com.ssafy.togetherhomt.user.auth.LoginDto;
import com.ssafy.togetherhomt.user.info.SignupDto;
import com.ssafy.togetherhomt.user.info.UpdateDto;
import com.ssafy.togetherhomt.util.Mailing.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MailingService mailingService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MailingService mailingService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailingService = mailingService;
    }

    @Transactional
    public String signup(SignupDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null)
            return "failure";


        User user = User.builder()
                .email(userDto.getEmail())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .username(userDto.getUsername())
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        return "success";
    }

    public SignupDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null)
            return null;

        if (!bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword()))
            return null;

        return new SignupDto(user.getEmail(), user.getPassword(), user.getUsername());
    }

    @Transactional
    public void withdraw(String email){
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
    }

    @Transactional
    public void update(String email, @Valid UpdateDto updateDto){
        User user = userRepository.findByEmail(email);
        user.setUsername(updateDto.getUsername());
        user.setIntroduce(updateDto.getIntroduce());
        user.setProfile_url(updateDto.getProfile_url());
        userRepository.save(user);
    }

    @Transactional
    public void passwordUpdate(String email,LoginDto loginDto){
        User user = userRepository.findByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(loginDto.getPassword()));
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

    public User getProfile(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }
}

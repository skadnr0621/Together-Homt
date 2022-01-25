package com.ssafy.togetherhomt.user;

import com.ssafy.togetherhomt.user.auth.LoginDto;
import com.ssafy.togetherhomt.user.info.SignupDto;
import com.ssafy.togetherhomt.user.info.UpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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



}

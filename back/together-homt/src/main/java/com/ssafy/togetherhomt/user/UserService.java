package com.ssafy.togetherhomt.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String signup(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            return "failure";
        }

        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword()) // 인코딩 필요
                .username(userDto.getUsername())
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        return "success";
    }

    public UserDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());

        if (user == null)
            return null;
        if (!user.getPassword().equals(loginDto.getPassword()))
            return null;

        return new UserDto(user.getEmail(), user.getPassword(), user.getUsername());
    }

}

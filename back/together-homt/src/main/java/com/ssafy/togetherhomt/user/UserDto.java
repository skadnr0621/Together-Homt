package com.ssafy.togetherhomt.user;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto { // 회원가입

    @NotNull
    @Size(max = 50)
    private String email;

    @NotNull
    @Size(min = 6, max = 15)
    private String password;

    @NotNull
    @Size(min = 3, max = 10)
    private String username;
}

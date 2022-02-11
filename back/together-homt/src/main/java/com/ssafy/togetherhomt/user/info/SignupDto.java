package com.ssafy.togetherhomt.user.info;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto { // 회원가입

    @NotNull
    private String email;

    @NotNull
    @Size(min = 6, max = 15)
    private String password;

    @NotNull
    @Size(min = 3, max = 10)
    private String username;

}

package com.ssafy.togetherhomt.user;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto { // 로그인
    @NotNull
    @Size(max = 50)
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;
}

package com.ssafy.togetherhomt.user;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(max = 50)
    private String email;

    @NotNull
    @Size(min = 3, max = 10)
    private String username;

}

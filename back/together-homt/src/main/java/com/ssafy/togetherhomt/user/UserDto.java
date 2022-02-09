package com.ssafy.togetherhomt.user;

import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @ApiParam(value = "사용자 계정 주소")
    @NotNull
    @Size(max = 50)
    private String email;

    @ApiParam(value = "사용자 계정 별명(nickname)")
    @NotNull
    @Size(min = 3, max = 10)
    private String username;

    private String introduce;

    private String imagePath;

    private Long cntFollower;
    private List<UserDto> followers;
    private Long cntFollowing;
    private List<UserDto> followings;


    public UserDto(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.introduce = user.getIntroduce();
        this.imagePath = user.getImagePath();
    }

}

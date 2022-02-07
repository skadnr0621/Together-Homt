package com.ssafy.togetherhomt.lhy.user;

import com.ssafy.togetherhomt.user.User;
import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto2 {

    @ApiParam(value = "사용자 계정 주소")
    @NotNull
    @Size(max = 50)
    private String email;

    @ApiParam(value = "사용자 계정 별명(nickname)")
    @NotNull
    @Size(min = 3, max = 10)
    private String username;

    private String introduce;

    private String profileUrl;

    private int nFollower;
    private int nFollowing;

    public UserDto2(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.introduce = user.getIntroduce();
        this.profileUrl = user.getProfile_url();
//        this.followers = user.getFollower();
    }

    public UserDto2(User user, boolean detailed) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.introduce = user.getIntroduce();
        this.profileUrl = user.getProfile_url();

        if (detailed) {
            nFollower = user.getFollower().size();
            nFollowing = user.getFollowing().size();
        }
    }

    public UserDto2(String email, String username) {
        this.email = email;
        this.username = username;
    }

}

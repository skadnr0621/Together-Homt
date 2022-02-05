package com.ssafy.togetherhomt.gkuer.dto;

import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {
    @ApiParam(value = "유저네임")
    private String username;

    @ApiParam(value = "프로필사진")
    private String profile_url;

    @ApiParam(value = "자기소개")
    private String introduce;

    @ApiParam(value = "유저이메일")
    private String email;

    @ApiParam(value = "팔로워수")
    private Long follower_cnt;
}

package com.ssafy.togetherhomt.gkuer.dto;

import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowListDto {

    @ApiParam(value = "유저이메일")
    private String email;

    @ApiParam(value = "유저이름")
    private String username;

    @ApiParam(value = "유저프로필사진")
    private String profile_url;

    @ApiParam(value = "자기소개")
    private String introduce;
}

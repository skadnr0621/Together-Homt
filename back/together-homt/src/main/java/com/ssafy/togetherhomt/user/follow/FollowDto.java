package com.ssafy.togetherhomt.user.follow;

import com.ssafy.togetherhomt.user.info.UserDto;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class FollowDto {

    @ApiParam(value = "팔로우를 하는 사람")
    private UserDto follower;   // 팔로우를 하는 사람

    @ApiParam(value = "팔로우를 하는 대상")
    private UserDto following;  // 팔로우를 하는 대상

}

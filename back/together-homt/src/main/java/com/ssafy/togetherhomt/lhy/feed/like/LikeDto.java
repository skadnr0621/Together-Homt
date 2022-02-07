package com.ssafy.togetherhomt.lhy.feed.like;

import com.ssafy.togetherhomt.feed.like.Like;
import com.ssafy.togetherhomt.lhy.user.UserDto;
import com.ssafy.togetherhomt.user.User;

public class LikeDto {

    private UserDto liker;

    public LikeDto(Like like) {
        liker = new UserDto(like.getUser());
    }

}

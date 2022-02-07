package com.ssafy.togetherhomt.lhy.feed.like;

import com.ssafy.togetherhomt.feed.like.Like;
import com.ssafy.togetherhomt.lhy.user.UserDto2;

public class LikeDto2 {

    private UserDto2 liker;

    public LikeDto2(Like like) {
        liker = new UserDto2(like.getUser());
    }

}

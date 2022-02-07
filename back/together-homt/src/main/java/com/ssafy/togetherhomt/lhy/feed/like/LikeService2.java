package com.ssafy.togetherhomt.lhy.feed.like;

import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.like.Like;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LikeService2 {

    private LikeRepository2 likeRepository2;


    public List<LikeDto2> getFeedLikes(Feed feed) {
        List<LikeDto2> likeList = new ArrayList<>();
        for (Like like : likeRepository2.findByFeed(feed))
            likeList.add(new LikeDto2(like));
        return likeList;
    }

}

package com.ssafy.togetherhomt.lhy.feed.like;

import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.like.Like;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LikeService {

    private LikeRepository likeRepository;


    public List<LikeDto> getFeedLikes(Feed feed) {
        List<LikeDto> likeList = new ArrayList<>();
        for (Like like : likeRepository.findByFeed(feed))
            likeList.add(new LikeDto(like));
        return likeList;
    }

}

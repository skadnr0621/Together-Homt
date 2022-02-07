package com.ssafy.togetherhomt.lhy.feed;

import com.ssafy.togetherhomt.feed.Feed;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FeedService {



    public FeedDto getFeed(Feed feed) {
        return new FeedDto(feed);
    }

}

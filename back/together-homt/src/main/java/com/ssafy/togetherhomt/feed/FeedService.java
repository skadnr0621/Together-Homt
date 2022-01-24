package com.ssafy.togetherhomt.feed;

import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedService {

    public FeedRepository feedRepository;
    public UserRepository userRepository;

    @Autowired
    public FeedService(FeedRepository feedRepository, UserRepository userRepository) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
    }

    public List<FeedDto> main(String email) {

        User user = userRepository.findByEmail(email);
        List<FeedDto> feeds = new ArrayList<>();

        // ** 팔로잉 추가 필요 ** //
        for (Feed feed : feedRepository.findAll()) {
            FeedDto feedDto = new FeedDto();

            feedDto.setId(feed.getFeed_id());
            feedDto.setTitle(feed.getTitle());
            feedDto.setContent(feed.getContent());
            feedDto.setCreated_at(feed.getCreated_at());
            feedDto.setMedia_url(feed.getMedia_url());
            feedDto.setLike_cnt(feed.getLike_cnt());
            feedDto.setUserName(feed.getUser().getUsername());

            feeds.add(feedDto);
        }

        return feeds;
    }

    public List<FeedDto> profile(String email) {

        User user = userRepository.findByEmail(email);

        List<FeedDto> feeds = new ArrayList<>();
        for (Feed feed : feedRepository.findByUser(user)) {
            FeedDto feedDto = new FeedDto();

            feedDto.setId(feed.getFeed_id());
            feedDto.setTitle(feed.getTitle());
            feedDto.setContent(feed.getContent());
            feedDto.setCreated_at(feed.getCreated_at());
            feedDto.setMedia_url(feed.getMedia_url());
            feedDto.setLike_cnt(feed.getLike_cnt());
            feedDto.setUserName(feed.getUser().getUsername());

            feeds.add(feedDto);
        }

        return feeds;
    }
}

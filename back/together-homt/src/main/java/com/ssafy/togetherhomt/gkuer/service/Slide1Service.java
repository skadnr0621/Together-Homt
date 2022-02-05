package com.ssafy.togetherhomt.gkuer.service;

import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.FeedRepository;
import com.ssafy.togetherhomt.feed.tag.Tag;
import com.ssafy.togetherhomt.gkuer.dto.FeedListDto;
import com.ssafy.togetherhomt.gkuer.dto.UserListDto;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Slide1Service {

    private UserRepository userRepository;
    private FeedRepository feedRepository;

    @Autowired
    public Slide1Service(UserRepository userRepository, FeedRepository feedRepository) {
        this.userRepository = userRepository;
        this.feedRepository = feedRepository;
    }

    public List<UserListDto> userlist() {

        List<UserListDto> result = new ArrayList<>();

        List<User> allUser = userRepository.findAll();

        for (User user : allUser) {
            UserListDto userListDto = new UserListDto();

            userListDto.setUsername(user.getUsername());
            userListDto.setProfile_url(user.getProfile_url());
            userListDto.setIntroduce(user.getIntroduce());
            userListDto.setEmail(user.getEmail());
            userListDto.setFollower_cnt(user.getFollower().stream().count());

            result.add(userListDto);
        }

        return result;
    }

    public List<FeedListDto> feedlist() {

        List<FeedListDto> result = new ArrayList<>();

        List<Feed> allFeed = feedRepository.findAll();

        for (Feed feed : allFeed) {
            FeedListDto feedListDto = new FeedListDto();

            feedListDto.setFeed_id(feed.getFeed_id());
            feedListDto.setUsername(feed.getUser().getUsername());
            feedListDto.setMedia_url(feed.getMedia_url());
            feedListDto.setLike_cnt(feed.getLike_cnt());
            feedListDto.setContent(feed.getContent());

            List<String> tempTags = new ArrayList<>();
            for (Tag tag : feed.getTags()) {
                tempTags.add(tag.getName());
            };
            feedListDto.setTags(tempTags);

            feedListDto.setCreated_at(feed.getCreatedAt());

            result.add(feedListDto);
        }

        return result;
    }
}

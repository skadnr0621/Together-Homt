package com.ssafy.togetherhomt.gkuer.service;

import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.FeedRepository;
import com.ssafy.togetherhomt.feed.tag.Tag;
import com.ssafy.togetherhomt.gkuer.dto.FollowListDto;
import com.ssafy.togetherhomt.gkuer.dto.ProfileFeedDto;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import com.ssafy.togetherhomt.user.follow.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Slide3Service {

    private UserRepository userRepository;
    private FeedRepository feedRepository;

    @Autowired
    public Slide3Service(UserRepository userRepository, FeedRepository feedRepository) {
        this.userRepository = userRepository;
        this.feedRepository = feedRepository;
    }

    public List<FollowListDto> followerlist(String email) {
        User user = userRepository.findByEmail(email);

        List<FollowListDto> result = new ArrayList<>();

        for (Follow follow : user.getFollower()) {
            FollowListDto followerListDto = new FollowListDto();
            User follower = follow.getFollower();

            followerListDto.setEmail(follower.getEmail());
            followerListDto.setUsername(follower.getUsername());
            followerListDto.setProfile_url(follower.getProfile_url());
            followerListDto.setIntroduce(follower.getIntroduce());

            result.add(followerListDto);
        }

        return result;
    }

    public List<FollowListDto> followinglist(String email) {
        User user = userRepository.findByEmail(email);

        List<FollowListDto> result = new ArrayList<>();

        for (Follow follow : user.getFollowing()) {
            FollowListDto followingListDto = new FollowListDto();
            User following = follow.getFollowing();

            followingListDto.setEmail(following.getEmail());
            followingListDto.setUsername(following.getUsername());
            followingListDto.setProfile_url(following.getProfile_url());
            followingListDto.setIntroduce(following.getIntroduce());

            result.add(followingListDto);
        }

        return result;
    }

    public List<ProfileFeedDto> profilefeed(String email) {
        User user = userRepository.findByEmail(email);
        List<Feed> feeds = feedRepository.findByUser(user);
        List<ProfileFeedDto> result= new ArrayList<>();

        for (Feed feed : feeds) {
            ProfileFeedDto profileFeedDto = new ProfileFeedDto();

            profileFeedDto.setFeed_id(feed.getFeed_id());
            profileFeedDto.setMedia_url(feed.getMedia_url());
            profileFeedDto.setLike_cnt(feed.getLike_cnt());
            profileFeedDto.setContent(feed.getContent());

            List<String> tempTags = new ArrayList<>();
            for (Tag tag : feed.getTags()) {
                tempTags.add(tag.getName());
            };
            profileFeedDto.setTags(tempTags);

            profileFeedDto.setCreated_at(feed.getCreatedAt());

            result.add(profileFeedDto);
        }

        return result;
    }
}

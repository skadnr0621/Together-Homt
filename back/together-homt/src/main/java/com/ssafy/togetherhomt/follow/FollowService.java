package com.ssafy.togetherhomt.follow;

import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserDto2;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;


    // 나를 팔로우 하는 사람 검색
    public List<UserDto2> listFollowers(User me) {
        List<Follow> followers = followRepository.findByFollowing(me);

        List<UserDto2> followerList = new ArrayList<>();
        for (Follow follow : followers) {
            User follower = follow.getFollower();
            followerList.add(new UserDto2(follower.getEmail(), follower.getUsername()));
        }
        return followerList;
    }

    // 내가 팔로우 하는 사람 검색
    public List<UserDto2> listFollowings(User me) {
        List<Follow> followings = followRepository.findByFollower(me);

        List<UserDto2> followingList = new ArrayList<>();
        for (Follow follow : followings) {
            User following = follow.getFollowing();
            followingList.add(new UserDto2(following.getEmail(), following.getUsername()));
        }
        return followingList;
    }

    public Follow follow(User me, User you) {
        Follow follow = new Follow();
        follow.setFollower(me);
        follow.setFollowing(you);

        return followRepository.save(follow);
    }

}

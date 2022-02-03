package com.ssafy.togetherhomt.user.follow;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.info.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FollowService {

    private FollowRepository followRepository;

    private CommonService commonService;


    // 나를 팔로우 하는 사람 검색
    public List<UserDto> listFollowers() {
        List<Follow> followers = followRepository.findByFollowing(commonService.getLoginUser());

        List<UserDto> followerList = new ArrayList<>();
        for (Follow follow : followers) {
            User follower = follow.getFollower();
            followerList.add(new UserDto(follower.getEmail(), follower.getUsername()));
        }
        return followerList;
    }

    // 내가 팔로우 하는 사람 검색
    public List<UserDto> listFollowings() {
        List<Follow> followings = followRepository.findByFollower(commonService.getLoginUser());

        List<UserDto> followingList = new ArrayList<>();
        for (Follow follow : followings) {
            User following = follow.getFollowing();
            followingList.add(new UserDto(following.getEmail(), following.getUsername()));
        }
        return followingList;
    }

    @Transactional
    public Follow follow(User you) {
        User me = commonService.getLoginUser();
        if (followRepository.findByFollowerAndFollowing(me, you) != null)
            return null;

        Follow follow = new Follow();
        follow.setFollower(me);
        follow.setFollowing(you);

        return followRepository.save(follow);
    }

    @Transactional
    public void unfollow(User you) {
        User me = commonService.getLoginUser();
        Follow follow = followRepository.findByFollowerAndFollowing(me, you);
        if (follow != null)
            followRepository.delete(follow);
    }

}


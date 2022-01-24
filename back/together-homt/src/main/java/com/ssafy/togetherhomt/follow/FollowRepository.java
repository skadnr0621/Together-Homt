package com.ssafy.togetherhomt.follow;

import com.ssafy.togetherhomt.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    // 나를 팔로우 하는 사람 검색
    List<Follow> findByFollowing(User following);

    // 내가 팔로우 하는 사람 검색
    List<Follow> findByFollower(User follower);

}

package com.ssafy.togetherhomt.lhy.follow;

import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository2 extends JpaRepository<Follow, Long> {

    // 나를 팔로우 하는 사람 수 검색
    Long countByFollowing(User user);
    // 내가 팔로우 하는 사람 수 검색
    Long countByFollower(User user);

}

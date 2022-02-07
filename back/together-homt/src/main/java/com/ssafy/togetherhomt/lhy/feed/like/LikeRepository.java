package com.ssafy.togetherhomt.lhy.feed.like;

import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByFeed(Feed feed);

}

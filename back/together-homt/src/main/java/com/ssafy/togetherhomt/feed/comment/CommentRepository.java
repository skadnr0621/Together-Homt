package com.ssafy.togetherhomt.feed.comment;

import com.ssafy.togetherhomt.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByFeed(Feed feed);
}

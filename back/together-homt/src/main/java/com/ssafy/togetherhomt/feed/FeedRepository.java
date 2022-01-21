package com.ssafy.togetherhomt.feed;

import com.ssafy.togetherhomt.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findByUser(User user);
}

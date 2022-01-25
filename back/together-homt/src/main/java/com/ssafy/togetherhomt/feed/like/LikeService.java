package com.ssafy.togetherhomt.feed.like;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.FeedRepository;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private UserRepository userRepository;
    private FeedRepository feedRepository;
    private LikeRepository likeRepository;

    @Autowired
    public LikeService(UserRepository userRepository, FeedRepository feedRepository, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.feedRepository = feedRepository;
        this.likeRepository = likeRepository;
    }

    public String likeFeed(Long feed_id) {

        // Get User
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());

        // Get Feed
        Feed feed = feedRepository.getById(feed_id);

        // 좋아요 db 반영

        // 좋아요 있을 때
        Like likeCheck = likeRepository.findByUserAndFeed(user, feed);
        if (likeCheck != null) {

            likeRepository.delete(likeCheck);

        } else { // 좋아요 없을 때

            Like like = Like.builder()
                    .user(user)
                    .feed(feed)
                    .build();

            likeRepository.save(like);
        }

//      // 좋아요 카운트 +1
        feed.setLike_cnt(feed.getLike_cnt()+1);

        return "success";
    }
}

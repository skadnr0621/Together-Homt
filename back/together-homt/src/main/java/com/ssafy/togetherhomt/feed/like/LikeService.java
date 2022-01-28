package com.ssafy.togetherhomt.feed.like;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.FeedRepository;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    private UserRepository userRepository;
    private FeedRepository feedRepository;
    private LikeRepository likeRepository;
    private CommonService commonService;

    @Autowired
    public LikeService(UserRepository userRepository, FeedRepository feedRepository, LikeRepository likeRepository, CommonService commonService) {
        this.userRepository = userRepository;
        this.feedRepository = feedRepository;
        this.likeRepository = likeRepository;
        this.commonService = commonService;
    }


    public String likeFeed(Long feed_id) {

        System.out.println("feed_id = " + feed_id);

        // Get User
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());

        // Get Feed
        Feed feed = feedRepository.getById(feed_id);

        // Check Status Like
        if (likeRepository.findByUserAndFeed(user, feed) != null) {
            return "이미 사용자가 좋아요 중입니다.";
        }

        // 좋아요 db 반영
        Like like = Like.builder()
                .user(user)
                .feed(feed)
                .build();

        likeRepository.save(like);

        // 좋아요 카운트 +1
        feed.setLike_cnt(feed.getLike_cnt()+1);
        feedRepository.save(feed);

        return "success";
    }

    public String undoLikeFeed(Long feed_id) {
        Optional<Feed> optFeed = feedRepository.findById(feed_id);
        if (!optFeed.isPresent())
            return "error";

        Feed feed = optFeed.get();
        User me = commonService.getLoginUser();

        Like like = likeRepository.findByUserAndFeed(me, feed);
        if (like != null) {

            likeRepository.delete(like);

            // 좋아요 카운트 -1
            feed.setLike_cnt(feed.getLike_cnt() - 1);
            feedRepository.save(feed);
        }
        return "success";
    }
}

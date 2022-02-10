package com.ssafy.togetherhomt.feed.like;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.FeedRepository;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import com.ssafy.togetherhomt.user.follow.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private UserRepository userRepository;
    private FeedRepository feedRepository;
    private LikeRepository likeRepository;
    private CommonService commonService;
    private FollowRepository followRepository;

    @Autowired
    public LikeService(UserRepository userRepository, FeedRepository feedRepository, LikeRepository likeRepository, CommonService commonService, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.feedRepository = feedRepository;
        this.likeRepository = likeRepository;
        this.commonService = commonService;
        this.followRepository = followRepository;
    }

    public ResponseEntity<?> likeFeed(Long feed_id) {

        // Get User
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());

        // Get Feed
        Feed feed = feedRepository.getById(feed_id);

        // Check Status Like
        if (likeRepository.findByUserAndFeed(user, feed) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Build & Save Like
        Like like = Like.builder()
                .user(user)
                .feed(feed)
                .build();

        likeRepository.save(like);

        // Update Feed's like_cnt
        feed.setLike_cnt(feed.getLike_cnt()+1);
        feedRepository.save(feed);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<LikeUserDto> getLikeUser(Long feed_id) {

        // Get User
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());

        // Get Feed
        Optional<Feed> feed = feedRepository.findById(feed_id);

        List<LikeUserDto> result = new ArrayList<>();

        for (Like like : likeRepository.findAllByFeed(feed.get())) {
            LikeUserDto likeUserDto = new LikeUserDto();

            likeUserDto.setProfile_url(like.getUser().getImagePath());
            likeUserDto.setEmail(like.getUser().getImagePath());
            likeUserDto.setUsernmae(like.getUser().getUsername());
            likeUserDto.setIntroduce(like.getUser().getIntroduce());

            // Check Following Relation
            if (followRepository.findByFollowerAndFollowing(user, like.getUser()) != null) {
                likeUserDto.setCheck_following(true);
            } else {
                likeUserDto.setCheck_following(false);
            }

            result.add(likeUserDto);
        }

        return result;
    }

    public ResponseEntity<?> undoLikeFeed(Long feed_id) {

        // Get Feed
        Optional<Feed> optFeed = feedRepository.findById(feed_id);
        Feed feed = optFeed.get();
        // Check Like Status
        if (!optFeed.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Get User
        User me = commonService.getLoginUser();

        // Get Like
        Like like = likeRepository.findByUserAndFeed(me, feed);

        if (like != null) {

            likeRepository.delete(like);

            // Update Feed's like_cnt
            feed.setLike_cnt(feed.getLike_cnt() - 1);
            feedRepository.save(feed);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

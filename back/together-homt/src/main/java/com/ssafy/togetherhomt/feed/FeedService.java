package com.ssafy.togetherhomt.feed;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedService {

    public FeedRepository feedRepository;
    public UserRepository userRepository;
    public CommentRepository commentRepository;

    @Autowired
    public FeedService(FeedRepository feedRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }


    public List<FeedDto> main(String email) {

        User user = userRepository.findByEmail(email);
        List<FeedDto> feeds = new ArrayList<>();

        // ** 팔로잉 추가 필요 ** //
        for (Feed feed : feedRepository.findAll()) {
            FeedDto feedDto = new FeedDto();

            feedDto.setId(feed.getFeed_id());
            feedDto.setTitle(feed.getTitle());
            feedDto.setContent(feed.getContent());
            feedDto.setCreated_at(feed.getCreated_at());
            feedDto.setMedia_url(feed.getMedia_url());
            feedDto.setLike_cnt(feed.getLike_cnt());
            feedDto.setUserName(feed.getUser().getUsername());

            feeds.add(feedDto);
        }

        return feeds;
    }

    public List<FeedDto> profile(String email) {

        User user = userRepository.findByEmail(email);

        List<FeedDto> feeds = new ArrayList<>();
        for (Feed feed : feedRepository.findByUser(user)) {
            FeedDto feedDto = new FeedDto();

            feedDto.setId(feed.getFeed_id());
            feedDto.setTitle(feed.getTitle());
            feedDto.setContent(feed.getContent());
            feedDto.setCreated_at(feed.getCreated_at());
            feedDto.setMedia_url(feed.getMedia_url());
            feedDto.setLike_cnt(feed.getLike_cnt());
            feedDto.setUserName(feed.getUser().getUsername());

            feeds.add(feedDto);
        }

        return feeds;
    }

    public List<CommentDto> getComment(Long feed_id) {

        Optional<Feed> feed = feedRepository.findById(feed_id);


        List<CommentDto> comments = new ArrayList<>();
        for (Comment comment : commentRepository.findByFeed(feed.get())) {
            CommentDto commentDto = new CommentDto();
            commentDto.setComment_id(comment.getComment_id());
            commentDto.setContent(comment.getContent());
            commentDto.setCreated_at(comment.getCreated_at());
            commentDto.setFeed_id(comment.getFeed().getFeed_id());
            commentDto.setUsername(comment.getUser().getUsername());

            comments.add(commentDto);
        }

        return comments;
    }

    public String postComment(Long feed_id, CommentDto commentDto) {
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());

        Optional<Feed> feed = feedRepository.findById(feed_id);
        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .feed(feed.get())
                .user(user)
                .build();
        commentRepository.save(comment);

        return "success";
    }

    public String deleteComment(Long comment_id) {
        commentRepository.deleteById(comment_id);
        return "success";
    }
}

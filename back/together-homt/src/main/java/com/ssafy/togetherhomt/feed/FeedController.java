package com.ssafy.togetherhomt.feed;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    private FeedService feedService;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping("/main")
    public ResponseEntity<List<FeedDto>> main(@RequestBody String email) {
        List<FeedDto> feeds = feedService.main(email);
        return ResponseEntity.ok(feeds);
    }

    @GetMapping("/profile")
    public ResponseEntity<List<FeedDto>> profile(@RequestBody String email) {
        List<FeedDto> feeds = feedService.profile(email);
        return ResponseEntity.ok(feeds);
    }

    @GetMapping("/{feed_id}/comment")
    public ResponseEntity<List<CommentDto>> getComment(@PathVariable("feed_id") Long feed_id) {
        List<CommentDto> comment = feedService.getComment(feed_id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/{feed_id}/comment")
    public String postComment(@PathVariable("feed_id") Long feed_id, @RequestBody CommentDto commentDto) {
        return feedService.postComment(feed_id, commentDto);
    }

    @DeleteMapping("/comment/{comment_id}")
    public String deleteComment(@PathVariable("comment_id") Long comment_id) {
        return feedService.deleteComment(comment_id);
    }
}

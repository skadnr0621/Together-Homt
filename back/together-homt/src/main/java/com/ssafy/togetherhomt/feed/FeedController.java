package com.ssafy.togetherhomt.feed;

import com.ssafy.togetherhomt.feed.comment.CommentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("피드 관련 기능 접근 방법")
@RestController
@RequestMapping("/feed")
public class FeedController {

    private FeedService feedService;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @ApiOperation(value = "피드 등록", notes = "피드 등록")
    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute("file") FeedDto feedDto){

        String result = feedService.create(feedDto);

        if (result.equals("success")) {
            return ResponseEntity.ok("success");
        } else {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

    @ApiOperation(value = "전체 피드 조회", notes = "로그인 한 사용자가 팔로우 하는 유저의 피드를 조회하여 목록으로 보여줍니다.")
    @GetMapping("/main")
    public ResponseEntity<List<FeedDto>> main(@RequestBody String email) {
        List<FeedDto> feeds = feedService.main(email);
        return ResponseEntity.ok(feeds);
    }

    @ApiOperation(value = "내 피드 조회", notes = "내가 작성한 피드를 조회합니다.")
    @GetMapping("/profile")
    public ResponseEntity<List<FeedDto>> profile(@RequestBody String email) {
        List<FeedDto> feeds = feedService.profile(email);
        return ResponseEntity.ok(feeds);
    }

    @ApiOperation(value = "피드 댓글 조회", notes = "피드에 등록된 댓글을 조회합니다.")
    @GetMapping("/{feed_id}/comment")
    public ResponseEntity<List<CommentDto>> getComment(@PathVariable("feed_id") Long feed_id) {
        List<CommentDto> comment = feedService.getComment(feed_id);
        return ResponseEntity.ok(comment);
    }

    @ApiOperation(value = "피드 댓글 작성", notes = "피드에 댓글을 작성하여 추가합니다.")
    @PostMapping("/{feed_id}/comment")
    public String postComment(@PathVariable("feed_id") Long feed_id, @RequestBody CommentDto commentDto) {
        return feedService.postComment(feed_id, commentDto);
    }

    @ApiOperation(value = "피드 댓글 삭제", notes = "피드에 작성한 댓글을 삭제합니다.")
    @DeleteMapping("/comment/{comment_id}")
    public String deleteComment(@PathVariable("comment_id") Long comment_id) {
        return feedService.deleteComment(comment_id);
    }


}

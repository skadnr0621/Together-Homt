package com.ssafy.togetherhomt.feed;

import com.ssafy.togetherhomt.feed.comment.CommentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(code = 400, message = "잘못된 요청입니다. 계정을 명시하지 않았거나 로그인 정보와 맞지 않습니다. 또는 존재하지 않는 미디어 파일입니다."),
            @ApiResponse(code = 200, message = "피드 생성에 성공하였습니다.")
    })
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
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드 삭제에 성공하였습니다.")
    })
    @DeleteMapping("/{feed_id}")
    public String feedDelete(@PathVariable("feed_id") Long feed_id){

        return feedService.feedDelete(feed_id);
    }

    @ApiOperation(value = "내 피드 조회", notes = "내가 작성한 피드를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 사용자의 피드 조회에 성공하였습니다.")
    })
    @GetMapping("/personal_feed/{email}")
    public ResponseEntity<List<FeedDto>> getPersonalFeed(@PathVariable String email) {
        List<FeedDto> feeds = feedService.getPersonalFeed(email);
        return ResponseEntity.ok(feeds);
    }

    @ApiOperation(value = "피드 댓글 조회", notes = "피드에 등록된 댓글을 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 피드의 댓글 조회에 성공하였습니다.")
    })
    @GetMapping("/{feed_id}/comment")
    public ResponseEntity<List<CommentDto>> getComment(@PathVariable("feed_id") Long feed_id) {
        List<CommentDto> comment = feedService.getComment(feed_id);
        return ResponseEntity.ok(comment);
    }

    @ApiOperation(value = "피드 댓글 작성", notes = "피드에 댓글을 작성하여 추가합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 피드에 성공적으로 댓글을 작성하였습니다.")
    })
    @PostMapping("/{feed_id}/comment")
    public String postComment(@PathVariable("feed_id") Long feed_id, @RequestBody CommentDto commentDto) {
        return feedService.postComment(feed_id, commentDto);
    }

    @ApiOperation(value = "피드 댓글 삭제", notes = "피드에 작성한 댓글을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 댓글의 삭제에 성공하였습니다.")
    })
    @DeleteMapping("/comment/{comment_id}")
    public String deleteComment(@PathVariable("comment_id") Long comment_id) {
        return feedService.deleteComment(comment_id);
    }


}

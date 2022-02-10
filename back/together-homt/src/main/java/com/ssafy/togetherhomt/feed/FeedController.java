package com.ssafy.togetherhomt.feed;

import com.ssafy.togetherhomt.feed.comment.CommentDto;
import com.ssafy.togetherhomt.feed.comment.CommentListDto;
import com.ssafy.togetherhomt.feed.tag.TagDto;
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


    //=========================Feed=========================//

    @ApiOperation(value = "피드 등록", notes = "피드 등록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드 생성에 성공하였습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다. 계정을 명시하지 않았거나 로그인 정보와 맞지 않습니다. 또는 존재하지 않는 미디어 파일입니다."),
            @ApiResponse(code = 500, message = "서버 에러입니다.")
    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute("file") FeedDto feedDto, TagDto tagDto){
        return feedService.create(feedDto,tagDto);
    }

    @ApiOperation(value = "피드 전체 조회", notes = "전체 피드를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드 조회에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @GetMapping("/all")
    public List<FeedDto> all() {
        return feedService.all();
    }

    @ApiOperation(value = "팔로우기반 피드 조회(메인)", notes = "로그인 한 사용자가 팔로우 하는 유저의 피드를 조회하여 목록으로 보여줍니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "팔로우 하는 피드 조회에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @GetMapping("/main")
    public List<FeedListDto> main() {
        return feedService.main();
    }

    @ApiOperation(value = "내 피드 조회", notes = "내가 작성한 피드를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 사용자의 피드 조회에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @GetMapping("/personal_feed/{email}")
    public ResponseEntity<List<FeedDto>> getPersonalFeed(@PathVariable String email) {
        return ResponseEntity.ok(feedService.getPersonalFeed(email));
    }

    @ApiOperation(value = "피드리스트 조회(검색)", notes = "전체 피드리스트(검색)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드리스트 조회 성공"),
            @ApiResponse(code = 500, message = "서버 에러입니다.")
    })
    @GetMapping("/feedlist")
    public ResponseEntity<List<FeedListDto>> feedlist() {
        return ResponseEntity.ok(feedService.feedlist());
    }

    @ApiOperation(value = "유저가 작성한 피드 리스트(프로필)", notes = "유저가 작성한 피드 리스트(프로필)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저가 작성한 피드 리스트 조회 성공"),
            @ApiResponse(code = 500, message = "서버 에러입니다.")
    })
    @GetMapping("/profilefeed/{email}")
    public ResponseEntity<List<FeedProfileDto>> profilefeed(@PathVariable String email) {
        return ResponseEntity.ok(feedService.profilefeed(email));
    }

    @ApiOperation(value = "피드 수정(조회)", notes = "피드 수정(조회)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드 수정에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버 에러입니다.")
    })
    @GetMapping("/updateinfo/{feed_id}")
    public ResponseEntity<?> updateinfo(@PathVariable Long feed_id){
        return ResponseEntity.ok(feedService.updateinfo(feed_id));
    }

    @ApiOperation(value = "피드 수정(실행)", notes = "피드 수정(실행)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드 수정에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버 에러입니다.")
    })
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody FeedDto feedDto){
        return feedService.update(feedDto);
    }

    @ApiOperation(value = "피드 삭제", notes = "요청한 피드를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드 삭제에 성공하였습니다."),
            @ApiResponse(code = 400, message = "요청한 피드가 없습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @DeleteMapping("/{feed_id}")
    public ResponseEntity<?> feedDelete(@PathVariable("feed_id") Long feed_id){
        return feedService.feedDelete(feed_id);
    }

    //=========================Comment=========================//

    @ApiOperation(value = "피드 댓글 작성", notes = "피드에 댓글을 작성하여 추가합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 피드에 성공적으로 댓글을 작성하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @PostMapping("/{feed_id}/comment")
    public ResponseEntity<?> postComment(@PathVariable("feed_id") Long feed_id, @RequestBody CommentDto commentDto) {
        return feedService.postComment(feed_id, commentDto);
    }

    @ApiOperation(value = "피드 댓글 조회", notes = "피드에 등록된 댓글을 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 피드의 댓글 조회에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @GetMapping("/{feed_id}/comment")
    public ResponseEntity<List<CommentListDto>> getComments(@PathVariable Long feed_id) {
        return ResponseEntity.ok(feedService.getComments(feed_id));
    }

    @ApiOperation(value = "피드 댓글 삭제", notes = "피드에 작성한 댓글을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 댓글의 삭제에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @DeleteMapping("/comment/{comment_id}")
    public ResponseEntity<?> deleteComment(@PathVariable("comment_id") Long comment_id) {
        return feedService.deleteComment(comment_id);
    }
}

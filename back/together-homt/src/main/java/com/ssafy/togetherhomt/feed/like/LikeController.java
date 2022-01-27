package com.ssafy.togetherhomt.feed.like;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("'좋아요' 기능 접근 방법")
@RestController
@RequestMapping("/like")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @ApiOperation(value = "피드 좋아요", notes = "피드에 '좋아요' 누르는 기능", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드를 성공적으로 좋아했습니다.")
    })
    @PostMapping({"/{feed_id}"})
    public String likeFeed(@PathVariable("feed_id") Long feed_id) {
        String result = likeService.likeFeed(feed_id);
        return result;
    }

    @ApiOperation(value = "피드 좋아요 취소", notes = "피드에 '좋아요' 누른 것을 취소하는 기능", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드를 성공적으로 싫어했습니다.")
    })
    @DeleteMapping({"/{feed_id}"})
    public String unlikeFeed(@PathVariable("feed_id") Long feed_id) {
        String result = likeService.undoLikeFeed(feed_id);
        return result;
    }
}

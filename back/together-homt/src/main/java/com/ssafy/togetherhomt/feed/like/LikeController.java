package com.ssafy.togetherhomt.feed.like;

import com.ssafy.togetherhomt.feed.FeedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping({"/{feed_id}"})
    public String likeFeed(@PathVariable("feed_id") Long feed_id) {
        String result = likeService.likeFeed(feed_id);
        return result;
    }

    @DeleteMapping({"/{feed_id}"})
    public String unlikeFeed(@PathVariable("feed_id") Long feed_id) {
        String result = likeService.undoLikeFeed(feed_id);
        return result;
    }
}

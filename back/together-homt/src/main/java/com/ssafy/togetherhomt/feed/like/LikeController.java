package com.ssafy.togetherhomt.feed.like;

import com.ssafy.togetherhomt.feed.FeedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {

    private LikeService likeService;
 
    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public String likeFeed(@RequestBody Long feed_id) {
        String result = likeService.likeFeed(feed_id);
        return result;
    }

    @DeleteMapping
    public String unlikeFeed(@RequestBody Long feed_id) {
        String result = likeService.likeFeed(feed_id);
        return result;
    }
}

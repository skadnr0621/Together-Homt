package com.ssafy.togetherhomt.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}

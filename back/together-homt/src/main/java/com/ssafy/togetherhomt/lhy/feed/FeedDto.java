package com.ssafy.togetherhomt.lhy.feed;

import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.tag.Tag;
import com.ssafy.togetherhomt.lhy.feed.tag.TagDto;
import com.ssafy.togetherhomt.lhy.user.UserDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeedDto {

    private final Long feedId;

    private final UserDto writer;

    private final String mediaUrl;
    private final String content;
    private final LocalDateTime createdAt;
    private final List<TagDto> tagList;

    private final Long likeCnt;


    public FeedDto(Feed feed) {
        feedId = feed.getFeed_id();

        writer = new UserDto(feed.getUser());

        mediaUrl = feed.getMedia_url();
        content = feed.getContent();
        createdAt = feed.getCreatedAt();

        tagList = new ArrayList<>();
        for (Tag tag : feed.getTags())
            tagList.add(new TagDto(tag));

        likeCnt = feed.getLike_cnt();
    }

}

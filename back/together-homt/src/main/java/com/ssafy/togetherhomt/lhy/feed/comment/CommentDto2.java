package com.ssafy.togetherhomt.lhy.feed.comment;

import com.ssafy.togetherhomt.feed.comment.Comment;
import com.ssafy.togetherhomt.lhy.user.UserDto2;

import java.time.LocalDateTime;

public class CommentDto2 {

    private UserDto2 writer;

    private String content;
    private LocalDateTime createdAt;


    public CommentDto2(Comment comment) {
        writer = new UserDto2(comment.getUser());

        content = comment.getContent();
        createdAt = comment.getCreatedAt();
    }
}

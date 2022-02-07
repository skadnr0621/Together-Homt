package com.ssafy.togetherhomt.lhy.feed.comment;

import com.ssafy.togetherhomt.feed.comment.Comment;
import com.ssafy.togetherhomt.lhy.user.UserDto;

import java.time.LocalDateTime;

public class CommentDto {

    private UserDto writer;

    private String content;
    private LocalDateTime createdAt;


    public CommentDto(Comment comment) {
        writer = new UserDto(comment.getUser());

        content = comment.getContent();
        createdAt = comment.getCreatedAt();
    }
}

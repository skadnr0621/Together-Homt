package com.ssafy.togetherhomt.feed.comment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long comment_id;

    private LocalDateTime created_at;

    private String content;

    private String username;

    private Long feed_id;
}

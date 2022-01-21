package com.ssafy.togetherhomt.feed;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedDto {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime created_at;

    private String media_url;

    private Integer like_cnt;

    private String userName;
}

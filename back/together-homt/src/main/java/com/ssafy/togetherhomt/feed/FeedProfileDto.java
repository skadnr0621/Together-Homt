package com.ssafy.togetherhomt.feed;

import io.swagger.annotations.ApiParam;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedProfileDto {
    @ApiParam(value = "피드아이디")
    private Long feed_id;

    @ApiParam(value = "피드사진")
    private String media_url;

    @ApiParam(value = "좋아요개수")
    private Long like_cnt;

    @ApiParam(value = "피드내용")
    private String content;

    @ApiParam(value = "태그")
    private List<String> tags;

    @ApiParam(value = "게시 날짜시간")
    private LocalDateTime created_at;

    @ApiParam(value = "수정 날짜시간")
    private LocalDateTime updated_at;
}

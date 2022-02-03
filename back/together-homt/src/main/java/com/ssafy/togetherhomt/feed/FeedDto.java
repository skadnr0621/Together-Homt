package com.ssafy.togetherhomt.feed;

import io.swagger.annotations.ApiParam;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedDto {

    private Long id;

    @ApiParam(value = "피드 제목")
    @NotNull
    private String title;

    @ApiParam(value = "피드 내용")
    @NotNull
    private String content;

    @ApiParam(value = "피드 게시 시각")
    private LocalDateTime created_at;

    @ApiParam(value = "피드 첨부파일")
    private MultipartFile media;

    @ApiParam(value = "피드 첨부파일 저장 경로")
    private String media_url;

    @ApiParam(value = "피드 좋아요 수")
    private Long like_cnt;

    @ApiParam(value = "피드 작성자")
    private String userName;

}

package com.ssafy.togetherhomt.feed.comment;

import io.swagger.annotations.ApiParam;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentListDto {

    @ApiParam(value = "프로필사진")
    private String profile_url;

    @ApiParam(value = "유저이메일")
    private String email;

    @ApiParam(value = "유저이름")
    private String username;

    @ApiParam(value = "댓글내용")
    private String comment_content;

    @ApiParam(value = "댓글 작성 시각")
    private LocalDateTime created_at;
}

package com.ssafy.togetherhomt.feed;

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

    @NotNull
    private String title;

    @NotNull
    private String content;

    private LocalDateTime created_at;

    private MultipartFile media;

    private String media_url;

    private Integer like_cnt;

    private String userName;
}

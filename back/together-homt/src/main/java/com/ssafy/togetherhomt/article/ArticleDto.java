package com.ssafy.togetherhomt.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDto {

    @NotNull
    private String title;

    @NotNull
    private String content;

    private MultipartFile video;

    private String username;

}

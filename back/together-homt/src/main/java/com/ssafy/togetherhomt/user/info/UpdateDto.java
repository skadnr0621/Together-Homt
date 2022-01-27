package com.ssafy.togetherhomt.user.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDto {

    @Column(name = "username")
    private String username;

    @Column(name = "introduce")
    private String introduce;

    private MultipartFile media;

    @Column(name = "profile_url")
    private String profile_url;
}

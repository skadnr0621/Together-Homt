package com.ssafy.togetherhomt.feed;

import com.ssafy.togetherhomt.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

package com.ssafy.togetherhomt.feed;

import com.ssafy.togetherhomt.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feed")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feed {

    @Id
    @Column(name = "feed_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feed_id;

    private String title;

    private String content;

    @CreatedDate
    private LocalDateTime created_at;

    private String media_url;

    private Integer like_cnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

package com.ssafy.togetherhomt.feed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.togetherhomt.baseEntity.BaseTimeEntity;
import com.ssafy.togetherhomt.feed.comment.Comment;
import com.ssafy.togetherhomt.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "feed")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Feed extends BaseTimeEntity {

    @Id
    @Column(name = "feed_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feed_id;

    private String title;

    private String content;

    private String media_url;

    private Integer like_cnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "feed")
    @JsonIgnoreProperties({"feed"})
    private List<Comment> comments = new ArrayList<>();
}

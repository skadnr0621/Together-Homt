package com.ssafy.togetherhomt.article;

import com.ssafy.togetherhomt.baseEntity.BaseTimeEntity;
import com.ssafy.togetherhomt.user.User;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Article extends BaseTimeEntity {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long article_id;

    private String username;

    private String title;

    private String content;

    private String videoUrl;
}

package com.ssafy.togetherhomt.exercise;

import com.ssafy.togetherhomt.baseEntity.BaseTimeEntity;

import com.ssafy.togetherhomt.user.User;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Record extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_id;

    private Long date;

    private String video_url; // ??운동기록 남길거

    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

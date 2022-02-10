package com.ssafy.togetherhomt.exercise.record;

import com.ssafy.togetherhomt.exercise.exercise.Exercise;
import com.ssafy.togetherhomt.user.User;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_id;

    private String date;

    private String video_url; // ??운동기록 남길거

    private Long countCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}

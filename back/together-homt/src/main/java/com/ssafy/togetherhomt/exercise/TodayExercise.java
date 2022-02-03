package com.ssafy.togetherhomt.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.togetherhomt.user.User;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TodayExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TodayExercise_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Exercise exercise;
}

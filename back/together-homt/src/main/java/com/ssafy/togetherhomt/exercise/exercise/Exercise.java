package com.ssafy.togetherhomt.exercise.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.togetherhomt.exercise.record.Record;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.ssafy.togetherhomt.exercise.todayExercise.TodayExercise;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
    }
)
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exercise_id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    private String part;

    private String type;

    private Long exercise_time;

    private Long exercise_count;

    private String video_url;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"exercise"})
    private List<Record> records = new ArrayList<>();

    @OneToMany(mappedBy = "exercise")
    @JsonIgnoreProperties({"exercise"})
    private List<TodayExercise> todayExercises = new ArrayList<>();
}

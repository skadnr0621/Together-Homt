package com.ssafy.togetherhomt.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.togetherhomt.Record.Record;

import javax.persistence.*;

import com.ssafy.togetherhomt.Record.TodayExercise;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Exercise {
//    NECK_STRETCHING(0L, Type.Stretching, "neck", 0L, 20L, "naver.com"),
//    PUSHUP(1L, Type.WeightTraining,"chest", 0L, 40L, "naver.com");
//
//    private final Long exercise_id;
//    private final String part;
//    private final Type type;
//    private final Long exercise_time;
//    private final Long exercise_count;
//    private final String video_url;
//
//    Exercise(Long exercise_id, Type type, String part, Long exercise_time, Long exercise_count, String video_url){
//        this.exercise_id = exercise_id;
//        this.type = type;
//        this.part = part;
//        this.exercise_time = exercise_time;
//        this.exercise_count = exercise_count;
//        this.video_url = video_url;
//    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exercise_id;

    private String name;

    private String part;

    private String type;

    private Long exercise_time;

    private Long exercise_count;

    private String video_url;

    @OneToMany(mappedBy = "exercise")
    @JsonIgnoreProperties({"exercise"})
    private List<Record> records = new ArrayList<>();

    @OneToMany(mappedBy = "exercise")
    @JsonIgnoreProperties({"exercise"})
    private List<TodayExercise> todayExercises = new ArrayList<>();
}

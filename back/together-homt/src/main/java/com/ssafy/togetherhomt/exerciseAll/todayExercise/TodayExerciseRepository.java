package com.ssafy.togetherhomt.exerciseAll.todayExercise;

import com.ssafy.togetherhomt.exerciseAll.exercise.Exercise;
import com.ssafy.togetherhomt.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodayExerciseRepository extends JpaRepository<TodayExercise, Long> {
    List<TodayExercise> findByUser(User user);
    TodayExercise findByUserAndExercise(User user, Exercise exercise);
}

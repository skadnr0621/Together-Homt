package com.ssafy.togetherhomt.exercise;

import com.ssafy.togetherhomt.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodayExerciseRepository extends JpaRepository<TodayExercise, Long> {
    List<TodayExercise> findByUser(User user);
}

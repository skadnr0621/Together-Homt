package com.ssafy.togetherhomt.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService {

    ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    String exerciseAdd(ExerciseDto exerciseDto){
        Exercise exercise = Exercise.builder()
                .name(exerciseDto.getName())
                .part(exerciseDto.getPart())
                .exercise_time(exerciseDto.getExercise_time())
                .exercise_count(exerciseDto.getExercise_count())
                .video_url(exerciseDto.getVideo_url())
                .type(exerciseDto.getType())
                .build();

        exerciseRepository.save(exercise);
        return "success";
    }

    List<ExerciseDto> getExercise(){
        List<ExerciseDto> allExercise = new ArrayList<ExerciseDto>();

        for(Exercise exercise:exerciseRepository.findAll()){
            ExerciseDto exerciseDto = ExerciseDto.builder()
                    .name(exercise.getName())
                    .part(exercise.getPart())
                    .exercise_count(exercise.getExercise_count())
                    .exercise_time(exercise.getExercise_time())
                    .video_url(exercise.getVideo_url())
                    .type(exercise.getType())
                    .build();
            allExercise.add(exerciseDto);
        }
        return allExercise;
    }

    String deleteExercise(String exercise){
        Exercise exercise1 = exerciseRepository.findByName(exercise);
        exerciseRepository.delete(exercise1);
        return "success";
    }

    public String exerciseUpdate(String exercise, ExerciseDto exerciseDto) {
        Exercise newExercise = exerciseRepository.findByName(exercise);
        newExercise.setName(exerciseDto.getName());
        newExercise.setPart(exerciseDto.getPart());
        newExercise.setExercise_count(exerciseDto.getExercise_count());
        newExercise.setExercise_time(exerciseDto.getExercise_time());
        newExercise.setVideo_url(exerciseDto.getVideo_url());
        newExercise.setType(exerciseDto.getType());

        exerciseRepository.save(newExercise);

        return "success";
    }
}

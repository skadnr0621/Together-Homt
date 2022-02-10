package com.ssafy.togetherhomt.exercise.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }



    @PostMapping("/add")
    public ResponseEntity<?> exerciseAdd(@RequestBody ExerciseDto exerciseDto){
        String result = exerciseService.exerciseAdd(exerciseDto);
        if (result.equals("success")){
            return new ResponseEntity(result, HttpStatus.OK);
        }else{
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getExercise(){
        List<ExerciseDto> exercises = exerciseService.getExercise();
        return new ResponseEntity(exercises, HttpStatus.OK);
    }

    @DeleteMapping("/{exercise}")
    public ResponseEntity<?> deleteExercise(@PathVariable String exercise){
        String result = exerciseService.deleteExercise(exercise);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/{exercise}")
    public ResponseEntity<?> exerciseUpdate(@PathVariable String exercise, @RequestBody ExerciseDto exerciseDto){
        String result = exerciseService.exerciseUpdate(exercise, exerciseDto);
        if (result.equals("success")){
            return new ResponseEntity(result, HttpStatus.OK);
        }else{
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }
}

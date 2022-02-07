package com.ssafy.togetherhomt.exerciseAll.todayExercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/today")
public class TodayExerciseController {

    private TodayExerciseService todayExerciseService;

    @Autowired
    public TodayExerciseController(TodayExerciseService todayExerciseService) {
        this.todayExerciseService = todayExerciseService;
    }

    // 오늘의 운동 추가
    @PostMapping("/add")
    public ResponseEntity<?> exerciseAdd(@RequestBody TodayExerciseDto todayExerciseDto){
        List<TodayExerciseDto> result = todayExerciseService.exerciseAdd(todayExerciseDto);
        if(result.isEmpty()) return new ResponseEntity("이미 추가한 운동입니다.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    // 오늘의 운동 제거
    @DeleteMapping("/delete")
    public ResponseEntity<?> exerciseDelete(@RequestBody TodayExerciseDto todayExerciseDto){
        List<TodayExerciseDto> result = todayExerciseService.exerciseDelete(todayExerciseDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    // 한 사람이 오늘 할 운동 목록
    @GetMapping("/{email}")
    public ResponseEntity<?> personalTodayExercises(@PathVariable String email){
        List<TodayExerciseDto> result = todayExerciseService.todayExercises(email);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    // 오늘 운동 완료
    @PutMapping("/done")
    public ResponseEntity<?> exerciseDone(@RequestBody TodayExerciseDto todayExerciseDto){
        List<TodayExerciseDto> result = todayExerciseService.exerciseDone(todayExerciseDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}

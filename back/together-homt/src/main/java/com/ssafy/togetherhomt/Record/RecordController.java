package com.ssafy.togetherhomt.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    private RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    // 오늘의 운동 추가
    @PostMapping("/todayExercise/add")
    public ResponseEntity<?> exerciseAdd(@RequestBody TodayExerciseDto todayExerciseDto){
        List<String> result = recordService.exerciseAdd(todayExerciseDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    // 운동 완료 후 기록 생성
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RecordDto recordDto){

        String result = recordService.create(recordDto);

        if (result.equals("success")) {
            return ResponseEntity.ok("success");
        } else {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

    // 한 사람이 운동을 한 기록(달력)
    @GetMapping("/attendance/{email}")
    public ResponseEntity<List<String>> personalAttendance(@PathVariable String email){

        List<String> result = recordService.personalAttendance(email);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    // 오늘 운동 한 사람들
    @GetMapping("/todayAttendance")
    public ResponseEntity<?> todayAttendance(){
        List<String> result = recordService.todayAttendance();
        return new ResponseEntity(result, HttpStatus.OK);
    }
}

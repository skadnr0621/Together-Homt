package com.ssafy.togetherhomt.exerciseAll.record;

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
    @GetMapping("/{email}")
    public ResponseEntity<List<RecordDto>> personalRecords(@PathVariable String email){

        List<RecordDto> result = recordService.personalRecord(email);

        return new ResponseEntity(result, HttpStatus.OK);
    }



}

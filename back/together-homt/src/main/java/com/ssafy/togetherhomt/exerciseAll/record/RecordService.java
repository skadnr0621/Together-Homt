package com.ssafy.togetherhomt.exerciseAll.record;

import com.ssafy.togetherhomt.exerciseAll.attendance.Attendance;
import com.ssafy.togetherhomt.exerciseAll.attendance.AttendanceRepository;
import com.ssafy.togetherhomt.exerciseAll.todayExercise.TodayExerciseRepository;
import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.exerciseAll.exercise.*;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class RecordService {

    public UserRepository userRepository;
    public RecordRepository recordRepository;
    public AttendanceRepository attendanceRepository;
    public TodayExerciseRepository todayExerciseRepository;
    public ExerciseRepository exerciseRepository;

    @Autowired
    public RecordService(UserRepository userRepository, RecordRepository recordRepository, AttendanceRepository attendanceRepository, TodayExerciseRepository todayExerciseRepository, ExerciseRepository exerciseRepository) {
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
        this.attendanceRepository = attendanceRepository;
        this.todayExerciseRepository = todayExerciseRepository;
        this.exerciseRepository = exerciseRepository;
    }




    // 내 운동 기록 남기기
    public String create(RecordDto recordDto){

        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());
        Exercise exercise = exerciseRepository.findByName(recordDto.getExercise());

        Record record = Record.builder()
                .user(user)
                .countCheck(recordDto.getCountCheck())
                .exercise(exercise)
                .build();
        String record_time = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        record.setDate(record_time);
        recordRepository.save(record);
        return "success";
    }

    // 개인 운동 기록
    public List<RecordDto> personalRecord(String email){
        User user = userRepository.findByEmail(email);
        List<Record> records = recordRepository.findAllByUser(user);
        List<RecordDto> personalRecords = new ArrayList<>();
        for(int i=0;i< records.size();i++){
            RecordDto recordDto = RecordDto.builder()
                    .date(records.get(i).getDate())
                    .countCheck(records.get(i).getCountCheck())
                    .exercise(records.get(i).getExercise().getName())
                    .build();
            personalRecords.add(recordDto);
        }
        return personalRecords;
    }




}

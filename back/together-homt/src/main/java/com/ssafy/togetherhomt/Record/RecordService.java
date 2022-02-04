package com.ssafy.togetherhomt.Record;

import com.ssafy.togetherhomt.Record.Attendance.Attendance;
import com.ssafy.togetherhomt.Record.Attendance.AttendanceRepository;
import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.exercise.*;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    // 오늘 내 운동 추가, 내 운동리스트 반환
    @Transactional
    public List<String> exerciseAdd(TodayExerciseDto todayExerciseDto) {

        System.out.println("todayExerciseDto = " + todayExerciseDto);
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());
        Exercise exercise = exerciseRepository.findByName(todayExerciseDto.getExercise());

        List<TodayExercise> exercises = todayExerciseRepository.findByUser(user);
        if(exercises.size()>0){
            for(TodayExercise ex:exercises ){
                if(ex.getExercise().getName().equals(todayExerciseDto.getExercise())) {
                    return new ArrayList<>();
                }
            }
        }

        TodayExercise todayExercise = TodayExercise.builder()
                .user(user)
                .exercise(exercise)
                .build();
        todayExerciseRepository.save(todayExercise);

        List<String> myExercises = new ArrayList<>();

        exercises = todayExerciseRepository.findByUser(user);
        for (TodayExercise ex : exercises) {
            myExercises.add(ex.getExercise().getName());
        }
        return myExercises;
    }

    public List<String> todayExercises(String email){
        User user = userRepository.findByEmail(email);
        List<String> myExercises = new ArrayList<>();

        List<TodayExercise> exercises = todayExerciseRepository.findByUser(user);
        for (TodayExercise ex : exercises) {
            myExercises.add(ex.getExercise().getName());
        }
        return myExercises;
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


//        Attendance attendance = attendanceRepository.findAttendanceByUserAndDate(user,record_time);
//        if (!attendance.getDone()) attendance.setDone(true);
        Attendance attendance = Attendance.builder()
                        .user(user)
                        .date(record_time)
                        .done(true)
                        .build();
        attendanceRepository.save(attendance);

        return "success";
    }

    // 개인 운동 날짜 기록
    public List<String> personalAttendance(String email){
        User user = userRepository.findByEmail(email);
        List<Attendance> attendances = attendanceRepository.findAllByUser(user);
        List<String> attendance_dates = new ArrayList<>();
        for(int i=0;i< attendances.size();i++){
            if(!attendance_dates.contains(attendances.get(i).getDate()));{
                attendance_dates.add(attendances.get(i).getDate());
            }
        }
        return attendance_dates;
    }

    public List<String> todayAttendance(){
        String record_time = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

        List<Attendance> attendances = attendanceRepository.findAllByDate(record_time);
        List<String> attendees = new ArrayList<>();
        for(int i=0;i< attendances.size();i++){
            if(!attendees.contains(attendances.get(i).getUser().getUsername())){
                attendees.add(attendances.get(i).getUser().getUsername());
            }
        }
        return attendees;
    }


}

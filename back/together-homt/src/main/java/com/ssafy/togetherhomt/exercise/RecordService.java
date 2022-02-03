package com.ssafy.togetherhomt.exercise;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
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

    @Autowired
    public RecordService(UserRepository userRepository, RecordRepository recordRepository, AttendanceRepository attendanceRepository, TodayExerciseRepository todayExerciseRepository) {
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
        this.attendanceRepository = attendanceRepository;
        this.todayExerciseRepository = todayExerciseRepository;
    }

    public String create(RecordDto recordDto){

        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());

        Record record = Record.builder()
                .user(user)
                .countCheck(recordDto.getCountCheck())
                .exercise(Exercise.valueOf(recordDto.getExercise()))
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

    public List<String> personalAttendance(String email){
        User user = userRepository.findByEmail(email);
        List<Attendance> attendances = attendanceRepository.findAllByUser(user);
        List<String> attendance_dates = new ArrayList<>();
        for(int i=0;i< attendances.size();i++){
            attendance_dates.add(attendances.get(i).getDate());
        }
        return attendance_dates;
    }

    public List<String> todayAttendance(){
        String record_time = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

        List<Attendance> attendances = attendanceRepository.findAllByDate(record_time);
        List<String> attendees = new ArrayList<>();
        for(int i=0;i< attendances.size();i++){
            attendees.add(attendances.get(i).getUser().getUsername());
        }
        return attendees;
    }

    @Transactional
    public List<String> exerciseAdd(TodayExerciseDto todayExerciseDto) {

        System.out.println("todayExerciseDto = " + todayExerciseDto);
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());

        List<TodayExercise> exercises = todayExerciseRepository.findByUser(user);
        for(TodayExercise exercise:exercises ){
            if(exercise.getExercise().name().equals(todayExerciseDto.getExercise())) {
                return null;
            }
        }

        TodayExercise todayExercise = TodayExercise.builder()
                .user(user)
                .exercise(Exercise.valueOf(todayExerciseDto.getExercise()))
                .build();
        todayExerciseRepository.save(todayExercise);

        List<String> myExercises = new ArrayList<>();

        exercises = todayExerciseRepository.findByUser(user);
        for (TodayExercise exercise : exercises) {
            myExercises.add(exercise.getExercise().name());
        }
        return myExercises;
    }
}

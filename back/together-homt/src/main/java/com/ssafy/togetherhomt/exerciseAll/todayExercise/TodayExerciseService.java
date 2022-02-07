package com.ssafy.togetherhomt.exerciseAll.todayExercise;

import com.ssafy.togetherhomt.exerciseAll.attendance.Attendance;
import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.exerciseAll.attendance.AttendanceRepository;
import com.ssafy.togetherhomt.exerciseAll.exercise.Exercise;
import com.ssafy.togetherhomt.exerciseAll.exercise.ExerciseDto;
import com.ssafy.togetherhomt.exerciseAll.exercise.ExerciseRepository;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class TodayExerciseService {

    UserRepository userRepository;
    TodayExerciseRepository todayExerciseRepository;
    AttendanceRepository attendanceRepository;
    ExerciseRepository exerciseRepository;
    CommonService commonService;

    @Autowired
    public TodayExerciseService(UserRepository userRepository, TodayExerciseRepository todayExerciseRepository, AttendanceRepository attendanceRepository, ExerciseRepository exerciseRepository, CommonService commonService) {
        this.userRepository = userRepository;
        this.todayExerciseRepository = todayExerciseRepository;
        this.attendanceRepository = attendanceRepository;
        this.exerciseRepository = exerciseRepository;
        this.commonService = commonService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void create(){
        System.out.println("todayexercise deleted!!!!!!!");
        todayExerciseRepository.deleteAll();
    }

    @Transactional
    public List<TodayExerciseDto> exerciseAdd(TodayExerciseDto todayExerciseDto) {

        User user = userRepository.findByEmail(commonService.getLoginUser().getEmail());
        Exercise exercise = exerciseRepository.findByName(todayExerciseDto.getExercise());
        // 이미 있는지 확인
        List<TodayExercise> exercises = todayExerciseRepository.findByUser(user);
        if(exercises.size()>0){
            for(TodayExercise ex:exercises ){
                if(ex.getExercise().getName().equals(todayExerciseDto.getExercise())) {
                    return getTodayExerciseDtos(user);
                }
            }
        }
        // 새 운동 추가
        TodayExercise todayExercise = TodayExercise.builder()
                .user(user)
                .exercise(exercise)
                .build();
        todayExerciseRepository.save(todayExercise);

        return getTodayExerciseDtos(user);
    }

    // 조회
    public List<TodayExerciseDto> todayExercises(String email){
        User user = userRepository.findByEmail(email);
        return getTodayExerciseDtos(user);
    }

    // 삭제
    public List<TodayExerciseDto> exerciseDelete(TodayExerciseDto todayExerciseDto) {

        User user = userRepository.findByEmail(commonService.getLoginUser().getEmail());
        Exercise exercise = exerciseRepository.findByName(todayExerciseDto.getExercise());

        TodayExercise todayExercise = todayExerciseRepository.findByUserAndExercise(user, exercise);
        todayExerciseRepository.delete(todayExercise);

        return getTodayExerciseDtos(user);
    }

    // 운동 하나 완료하기
    // 출석 체크
    public List<TodayExerciseDto> exerciseDone(TodayExerciseDto todayExerciseDto) {

        User user = userRepository.findByEmail(commonService.getLoginUser().getEmail());
        Exercise exercise = exerciseRepository.findByName(todayExerciseDto.getExercise());

        TodayExercise todayExercise = todayExerciseRepository.findByUserAndExercise(user, exercise);
        todayExercise.setDone(true);
        todayExerciseRepository.save(todayExercise);

        Attendance attendance = attendanceRepository.findByUser(user);
        attendance.setDone(true);
        attendanceRepository.save(attendance);

        return getTodayExerciseDtos(user);
    }

    // 오늘 운동 조회 메서드
    private List<TodayExerciseDto> getTodayExerciseDtos(User user) {
        List<TodayExerciseDto> myExercises = new ArrayList<>();

        List<TodayExercise> exercises = todayExerciseRepository.findByUser(user);
        for (TodayExercise ex : exercises) {
            TodayExerciseDto todayExerciseDto = TodayExerciseDto.builder()
                    .exercise(ex.getExercise().getName())
                    .done(ex.isDone())
                    .build();
            myExercises.add(todayExerciseDto);
        }
        return myExercises;
    }




}


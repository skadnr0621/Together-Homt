package com.ssafy.togetherhomt.exerciseAll.attendance;

import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class AttendanceService {

    private AttendanceRepository attendanceRepository;
    private UserRepository userRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void create(){
        System.out.println("attendance created!!!!!!!");
        List<User> users = userRepository.findAll();
        attendanceRepository.deleteAll();

        for(User user:users){
            Attendance attendance = Attendance.builder()
                    .user(user)
                    .done(false)
                    .build();
            attendanceRepository.save(attendance);
        }
    }


    public List<AttendanceDto> todayAttendance(){
        List<AttendanceDto> attendees = new ArrayList<>();
        for(Attendance attendance:attendanceRepository.findAll()){
            AttendanceDto attendanceDto = AttendanceDto.builder()
                    .user(attendance.getUser().getUsername())
                    .image(attendance.getUser().getProfile_url())
                    .done(attendance.getDone())
                    .build();
            attendees.add(attendanceDto);
        }
        return attendees;
    }
}

package com.ssafy.togetherhomt.Record.Attendance;

import com.ssafy.togetherhomt.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance findAttendanceByUserAndDate(User user, String date);
    List<Attendance> findAllByDate(String date);
    List<Attendance> findAllByUser(User user);
}

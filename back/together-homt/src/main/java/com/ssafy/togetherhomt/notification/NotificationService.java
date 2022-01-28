package com.ssafy.togetherhomt.notification;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    public NotificationRepository notificationRepository;
    public UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public NotificationDto sendNotification(NotificationDto notificationDto) throws Exception{

        // Get Login User
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User loginUser = userRepository.findByEmail(userTemp.getEmail());

        // Get From/To User
        User fromUser = userRepository.findByEmail(notificationDto.getFromEmail());
        User toUser = userRepository.findByEmail(notificationDto.getToEmail());

        // Check User
        if (loginUser != fromUser) {
            throw new RuntimeException();
        }

        // Save Notification
        Notification notification = Notification.builder()
                .fromId(fromUser)
                .toId(toUser)
                .notificationType(notificationDto.getNotificationType())
                .build();

        notificationRepository.save(notification);

        // Return Notification
        return notificationDto;
    }

    public List<NotificationDto> checkNotification() {

        // Get Login User
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User loginUser = userRepository.findByEmail(userTemp.getEmail());

        ArrayList<NotificationDto> resNotification = new ArrayList<>();


        for (Notification notification : notificationRepository.findByToIdEmail(loginUser.getEmail())) {

            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setFromEmail(notification.getFromId().getEmail());
            notificationDto.setToEmail(notification.getToId().getEmail());
            notificationDto.setNotificationType(notification.getNotificationType());
            notificationDto.setCreated_at(notification.getCreatedAt());

            resNotification.add(notificationDto);

            notificationRepository.delete(notification);
        }

        return resNotification;
    }
}

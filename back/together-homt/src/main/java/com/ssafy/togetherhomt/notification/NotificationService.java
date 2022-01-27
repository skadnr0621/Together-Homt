package com.ssafy.togetherhomt.notification;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationService {

    private CommonService commonService;

    private NotificationRepository notificationRepository;
    private UserRepository userRepository;


    /*** 알림 조회 ***/
    public List<NotificationDto> getNotification(User receiver) {
        List<NotificationDto> notificationList = new ArrayList<>();
        for (Notification notification : notificationRepository.findByReceiver(receiver))
            notificationList.add(new NotificationDto(notification));
        return notificationList;
    }

    /*** 알림 전송 ***/
    public Notification sendNotification(NotificationDto notificationDto) {
        User receiver = userRepository.findByEmail(notificationDto.getReceiver().getEmail());
        if (receiver == null)
            return null;

        User sender = commonService.getLoginUser();

        Notification notification = Notification.builder()
                .sender(sender)
                .receiver(receiver)
                .notificationType(notificationDto.getNotificationType())
                .build();
        return notificationRepository.save(notification);
    }

    /*** 알림 확인(삭제) ***/
    public Notification readNotification(Long notificationId) {
        Optional<Notification> optNotification = notificationRepository.findById(notificationId);
        if (!optNotification.isPresent())
            return null;

        Notification notification = optNotification.get();

        User me = commonService.getLoginUser();
        if (!me.getEmail().equals(notification.getReceiver().getEmail()))
            return null;

        notificationRepository.delete(notification);
        return notification;
    }

}

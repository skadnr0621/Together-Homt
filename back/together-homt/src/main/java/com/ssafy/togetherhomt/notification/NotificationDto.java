package com.ssafy.togetherhomt.notification;

import com.ssafy.togetherhomt.user.info.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private Long notificationId;
    private UserDto sender;
    private UserDto receiver;
    private NotificationType notificationType;
    private LocalDateTime sentDate;

    public NotificationDto(Notification notification) {
        this.notificationId = notification.getNotificationId();
        this.sender = new UserDto(notification.getSender());
        this.notificationType = notification.getNotificationType();
        this.sentDate = notification.getSentDate();
    }

}

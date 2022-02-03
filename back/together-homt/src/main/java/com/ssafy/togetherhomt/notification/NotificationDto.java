package com.ssafy.togetherhomt.notification;

import com.ssafy.togetherhomt.user.info.UserDto;
import io.swagger.annotations.ApiParam;
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

    @ApiParam(value = "알림 전송자")
    private UserDto sender;
    @ApiParam(value = "알림 수신자")
    private UserDto receiver;
    @ApiParam(value = "알림 유형 (팔로우 = FOLLOW / 좋아요 = LIKE / 재촉 = PUSH")
    private NotificationType notificationType;
    
    private LocalDateTime sentDate;

    public NotificationDto(Notification notification) {
        this.notificationId = notification.getNotificationId();
        this.sender = new UserDto(notification.getSender());
        this.notificationType = notification.getNotificationType();
        this.sentDate = notification.getSentDate();
    }

}

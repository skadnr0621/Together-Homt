package com.ssafy.togetherhomt.notification;

import com.ssafy.togetherhomt.user.User;
import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    @ApiParam(value = "송신자 이메일")
    private String fromEmail;

    @ApiParam(value = "수신자 이메일")
    private String toEmail;

    @ApiParam(value = "생성시간")
    private LocalDateTime created_at;

    @ApiParam(value = "운동 타입")
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
}

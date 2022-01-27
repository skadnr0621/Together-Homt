package com.ssafy.togetherhomt.notification;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;

@Service
@AllArgsConstructor
public class NotificationService {

    private CommonService commonService;


    public void sendNotification(User receiver) {
        User sender = commonService.getLoginUser();

//        Notification notification = new Notification();
//        notification.setSender(sender);
//        notification.setReceiver(receiver);
//        notification.setNotificationType(type);
    }

}

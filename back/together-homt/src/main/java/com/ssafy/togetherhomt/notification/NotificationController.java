package com.ssafy.togetherhomt.notification;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
@AllArgsConstructor
public class NotificationController {

    private NotificationService notificationService;

    private UserRepository userRepository;


//    @PostMapping
//    public ResponseEntity<String> sendNotification(@RequsetBody NotificationDto notificationDto) {
//        User receiver = userRepository.findByEmail(receiverEmail);
//        if (receiver == null)
//            return new ResponseEntity<>("올바르지 않은 수신자", HttpStatus.BAD_REQUEST);
//
//        notificationService.sendNotification(receiver);
//        return new ResponseEntity<>("알림이 전송되었습니다.", HttpStatus.OK);
//    }

}

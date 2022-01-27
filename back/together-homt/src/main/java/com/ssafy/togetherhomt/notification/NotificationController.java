package com.ssafy.togetherhomt.notification;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("알림 관련 기능 접근 방법")
@RestController
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @ApiOperation(value = "알림 전송", notes = "알림 전송")
    @ApiResponses({
            @ApiResponse(code = 200, message = "알림 전송에 성공하였습니다.")
    })
    @PostMapping("/send")
    public ResponseEntity<NotificationDto> sendNotification(@RequestBody NotificationDto notificationDto) throws Exception{
        return ResponseEntity.ok(notificationService.sendNotification(notificationDto));
    }

    @ApiOperation(value = "알림 확인", notes = "알림 확인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "알림 확인에 성공하였습니다.")
    })
    @GetMapping("/check")
    public ResponseEntity<List<NotificationDto>> checkNotification() {
        return ResponseEntity.ok(notificationService.checkNotification());
    }

}

package com.example.gp_back_end.Controller;

import com.example.gp_back_end.dto.NotificationRequest;
import com.example.gp_back_end.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/notifications")
    public List<NotificationRequest> getNotificationByUserId(@RequestParam String userId) {
        return notificationService.getNotificationByUserId(userId);
    }
}

package com.example.gp_back_end.services;

import com.example.gp_back_end.dto.NotificationRequest;
import com.example.gp_back_end.model.NotificationModel;
import com.example.gp_back_end.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationRequest> getNotificationByUserId(String userId) {
        return notificationRepository.findByUserId(userId)
                .stream()
                .map(notification -> new NotificationRequest(
                        notification.getTitle(),
                        notification.getMessage()
                ))
                .collect(Collectors.toList());
    }
}

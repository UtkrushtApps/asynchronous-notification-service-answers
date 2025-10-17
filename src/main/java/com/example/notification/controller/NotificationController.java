package com.example.notification.controller;

import com.example.notification.model.NotificationRequest;
import com.example.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        notificationService.sendAsyncNotification(request);
        return ResponseEntity.ok("Notification request received and is being processed asynchronously.");
    }
}

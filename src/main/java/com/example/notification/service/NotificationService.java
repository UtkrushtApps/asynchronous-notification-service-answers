package com.example.notification.service;

import com.example.notification.model.NotificationRequest;

public interface NotificationService {
    void sendAsyncNotification(NotificationRequest request);
}

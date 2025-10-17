package com.example.notification.service.impl;

import com.example.notification.model.NotificationRequest;
import com.example.notification.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private final Executor notificationExecutor;

    public NotificationServiceImpl(Executor notificationExecutor) {
        this.notificationExecutor = notificationExecutor;
    }

    @Override
    public void sendAsyncNotification(NotificationRequest request) {
        // Email
        CompletableFuture<Void> emailFuture = CompletableFuture.runAsync(() -> {
            try {
                sendEmail(request.getEmail(), request.getMessage());
                logger.info("Email sent successfully to {}", request.getEmail());
            } catch (Exception e) {
                logger.error("Failed to send email to {}: {}", request.getEmail(), e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }, notificationExecutor);
        emailFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                logger.error("[Callback] Email delivery status: FAILED for {}. Reason: {}", request.getEmail(), ex.getMessage());
            } else {
                logger.info("[Callback] Email delivery status: SUCCESS for {}", request.getEmail());
            }
        });
        // SMS
        CompletableFuture<Void> smsFuture = CompletableFuture.runAsync(() -> {
            try {
                sendSms(request.getPhoneNumber(), request.getMessage());
                logger.info("SMS sent successfully to {}", request.getPhoneNumber());
            } catch (Exception e) {
                logger.error("Failed to send SMS to {}: {}", request.getPhoneNumber(), e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }, notificationExecutor);
        smsFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                logger.error("[Callback] SMS delivery status: FAILED for {}. Reason: {}", request.getPhoneNumber(), ex.getMessage());
            } else {
                logger.info("[Callback] SMS delivery status: SUCCESS for {}", request.getPhoneNumber());
            }
        });
        // Push Notification
        CompletableFuture<Void> pushFuture = CompletableFuture.runAsync(() -> {
            try {
                sendPushNotification(request.getPushToken(), request.getMessage());
                logger.info("Push notification sent successfully to {}", request.getPushToken());
            } catch (Exception e) {
                logger.error("Failed to send push notification to {}: {}", request.getPushToken(), e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }, notificationExecutor);
        pushFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                logger.error("[Callback] Push delivery status: FAILED for {}. Reason: {}", request.getPushToken(), ex.getMessage());
            } else {
                logger.info("[Callback] Push delivery status: SUCCESS for {}", request.getPushToken());
            }
        });
    }

    // Dummy implementations
    private void sendEmail(String email, String message) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email address is empty");
        }
        Thread.sleep(1000); // Simulate delay
        // Simulate success
    }
    private void sendSms(String phoneNumber, String message) throws Exception {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number is empty");
        }
        Thread.sleep(800); // Simulate delay
        // Simulate success
    }
    private void sendPushNotification(String pushToken, String message) throws Exception {
        if (pushToken == null || pushToken.isEmpty()) {
            throw new IllegalArgumentException("Push token is empty");
        }
        Thread.sleep(700);
    }
}

package com.example.notification.model;

public class NotificationRequest {
    private String email;
    private String phoneNumber;
    private String pushToken;
    private String message;

    // getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NotificationRequest{" +
                "email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pushToken='" + pushToken + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

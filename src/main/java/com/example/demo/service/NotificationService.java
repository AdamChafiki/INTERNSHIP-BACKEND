package com.example.demo.service;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Method to send a notification to a specific user/topic
    public void sendNotification(String message) {
        // Send message to "/topic/notifications"
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}



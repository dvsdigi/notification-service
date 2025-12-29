package com.dvs.Notification.System.service;

import com.dvs.Notification.System.model.User;
import com.dvs.Notification.System.model.UserRole;
import com.dvs.Notification.System.repo.NotificationRepository;
import com.dvs.Notification.System.repo.UserRepository;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FCMService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    // Send to specific users
    public void sendToUsers(List<Long> userIds, String title, String body) {
        List<User> users = userRepository.findAllById(userIds);
        List<String> tokens = users.stream()
                .map(User::getFcmToken)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (!tokens.isEmpty()) {
            sendMulticastNotification(tokens, title, body);
        }
    }

    // Send to all users with specific role
    public void sendToRole(UserRole role, String title, String body) {
        List<User> users = userRepository.findByRole(role);
        List<String> tokens = users.stream()
                .map(User::getFcmToken)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (!tokens.isEmpty()) {
            sendMulticastNotification(tokens, title, body);
        }
    }

    private void sendMulticastNotification(List<String> tokens, String title, String body) {
        MulticastMessage message = MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .addAllTokens(tokens)
                .build();

        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            System.out.println(response.getSuccessCount() + " messages sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

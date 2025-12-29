package com.dvs.Notification.System.controller;

import com.dvs.Notification.System.dto.NotificationRequest;
import com.dvs.Notification.System.dto.TargetedNotificationRequest;
import com.dvs.Notification.System.model.UserRole;
import com.dvs.Notification.System.service.FCMService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class FcmController {
    private final FCMService fcmService;

    public FcmController(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    // School → Parents
    @PostMapping("/school/to-parents")
    public ResponseEntity<?> sendToParents(@RequestBody NotificationRequest request) {
        fcmService.sendToRole(UserRole.PARENT, request.getTitle(), request.getMessage());
        return ResponseEntity.ok("Notification sent to all parents");
    }

    // School → Students
    @PostMapping("/school/to-students")
    public ResponseEntity<?> sendToStudents(@RequestBody NotificationRequest request) {
        fcmService.sendToRole(UserRole.STUDENT, request.getTitle(), request.getMessage());
        return ResponseEntity.ok("Notification sent to all students");
    }

    // School → Teachers/Staff
    @PostMapping("/school/to-teachers")
    public ResponseEntity<?> sendToTeachers(@RequestBody NotificationRequest request) {
        fcmService.sendToRole(UserRole.TEACHER, request.getTitle(), request.getMessage());
        return ResponseEntity.ok("Notification sent to all teachers");
    }

    // Targeted notification to specific users
    @PostMapping("/send")
    public ResponseEntity<?> sendTargeted(@RequestBody TargetedNotificationRequest request) {
        fcmService.sendToUsers(request.getUserIds(),
                request.getTitle(), request.getMessage());
        return ResponseEntity.ok("Notification sent");
    }
}

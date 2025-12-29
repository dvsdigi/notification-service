package com.dvs.Notification.System.dto;

import com.dvs.Notification.System.model.NotificationPriority;
import com.dvs.Notification.System.model.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetedNotificationRequest {

    @NotEmpty(message = "At least one recipient is required")
    private List<Long> userIds; // List of user IDs to send notification to

    public List<Long> getUserIds() {
        return userIds;
    }

    public String getTitle() {
        return title;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMessage() {
        return message;
    }

    public NotificationPriority getPriority() {
        return priority;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Message is required")
    private String message;

    @NotNull(message = "Notification type is required")
    private NotificationType notificationType;

    private NotificationPriority priority = NotificationPriority.MEDIUM;

    private LocalDateTime scheduledAt; // Optional: for scheduling notifications

    private String imageUrl; // Optional: for rich notifications

    private String actionUrl; // Optional: deep link or action URL
}
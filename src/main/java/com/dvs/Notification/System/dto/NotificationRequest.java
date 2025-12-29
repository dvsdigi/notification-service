package com.dvs.Notification.System.dto;

import com.dvs.Notification.System.model.NotificationPriority;
import com.dvs.Notification.System.model.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
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

    public String getTitle() {
        return title;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public NotificationPriority getPriority() {
        return priority;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public String getMessage() {
        return message;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

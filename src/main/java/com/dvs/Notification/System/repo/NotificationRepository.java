package com.dvs.Notification.System.repo;

import com.dvs.Notification.System.model.Notification;
import com.dvs.Notification.System.model.NotificationType;


import com.dvs.Notification.System.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findBySender(User sender);

    Page<Notification> findBySender(User sender, Pageable pageable);

    List<Notification> findByNotificationType(NotificationType type);

    @Query("SELECT n FROM Notification n WHERE n.isSent = false AND n.scheduledAt <= :currentTime")
    List<Notification> findPendingScheduledNotifications(@Param("currentTime") LocalDateTime currentTime);

    @Query("SELECT n FROM Notification n WHERE n.sender.id = :senderId ORDER BY n.createdAt DESC")
    Page<Notification> findBySenderId(@Param("senderId") Long senderId, Pageable pageable);

    @Query("SELECT n FROM Notification n WHERE n.createdAt BETWEEN :startDate AND :endDate")
    List<Notification> findByDateRange(@Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.sender.id = :senderId AND n.isSent = true")
    long countSentNotificationsBySender(@Param("senderId") Long senderId);

    @Query("SELECT n FROM Notification n WHERE n.isSent = false ORDER BY n.createdAt ASC")
    List<Notification> findAllPendingNotifications();

    @Query("SELECT n FROM Notification n WHERE n.sender.id = :senderId AND n.notificationType = :type")
    List<Notification> findBySenderIdAndType(@Param("senderId") Long senderId,
                                             @Param("type") NotificationType type);
}
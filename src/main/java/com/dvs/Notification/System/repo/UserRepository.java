package com.dvs.Notification.System.repo;

import com.dvs.Notification.System.model.User;
import com.dvs.Notification.System.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findByRole(UserRole role);

    List<User> findByRoleAndIsActive(UserRole role, Boolean isActive);

    @Query("SELECT u FROM User u WHERE u.role = :role AND u.fcmToken IS NOT NULL AND u.isActive = true")
    List<User> findActiveUsersWithTokenByRole(@Param("role") UserRole role);

    @Query("SELECT u FROM User u WHERE u.fcmToken IS NOT NULL AND u.isActive = true")
    List<User> findAllActiveUsersWithToken();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role AND u.isActive = true")
    long countActiveUsersByRole(@Param("role") UserRole role);
}

package com.gms.backend.inventory.dashboard.repository;

import com.gms.backend.inventory.dashboard.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByIsRead(boolean isRead);
}
// NotificationController.java
package com.gms.backend.customer.dashboard.controller;

import com.gms.backend.customer.dashboard.dto.NotificationDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    // 4. NOTIFICATIONS
    @GetMapping
    public List<NotificationDTO> getNotifications() {
        return List.of(
                new NotificationDTO("20% Off AC Service", "2h ago"),
                new NotificationDTO("Job #1 is now in Progress", "5h ago")
        );
    }
}
package com.gms.backend.notification;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @GetMapping
    public List<Map<String, String>> getNotifications() {
        return List.of(
                Map.of("message", "20% Off AC Service", "time", "2h ago"),
                Map.of("message", "Job is In Progress", "time", "5h ago")
        );
    }
}
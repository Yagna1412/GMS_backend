package com.gms.backend.inventory.dashboard.controller;

import com.gms.backend.inventory.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
        return ResponseEntity.ok(dashboardService.getSummary());
    }

    @GetMapping("/stock-status")
    public ResponseEntity<?> getStockStatus() {
        return ResponseEntity.ok(dashboardService.getStockStatus());
    }

    @GetMapping("/pending-actions")
    public ResponseEntity<?> getPendingActions() {
        return ResponseEntity.ok(dashboardService.getPendingActions());
    }

    @GetMapping("/low-stock-items")
    public ResponseEntity<?> getLowStockItems() {
        return ResponseEntity.ok(dashboardService.getLowStockItems());
    }

    @GetMapping("/out-of-stock")
    public ResponseEntity<?> getOutOfStockItems() {
        return ResponseEntity.ok(dashboardService.getOutOfStockItems());
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam String q) {

        return ResponseEntity.ok(
                dashboardService.search(q)
        );
    }

    @GetMapping("/notifications")
    public ResponseEntity<?> getNotifications(
            @RequestParam(defaultValue = "false") boolean isRead) {

        return ResponseEntity.ok(
                dashboardService.getNotifications(isRead)
        );
    }
}
package com.gms.backend.serviceadvisor.dashboard.controller;


import com.gms.backend.serviceadvisor.dashboard.dto.*;
import com.gms.backend.serviceadvisor.dashboard.service.ServiceAdvisorDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard/service-advisor")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ServiceAdvisorDashboardController {

    private final ServiceAdvisorDashboardService serviceAdvisorDashboardService;

    @GetMapping("/kpis")
    public ResponseEntity<DashboardKpiResponseDto> getKpis() {
        return ResponseEntity.ok(serviceAdvisorDashboardService.getKpis());
    }

    @GetMapping("/today-schedule")
    public ResponseEntity<TodayScheduleResponseDto> getTodaySchedule() {
        return ResponseEntity.ok(serviceAdvisorDashboardService.getTodaySchedule());
    }

    @GetMapping("/pending-estimations")
    public ResponseEntity<PendingEstimationsResponseDto> getPendingEstimations() {
        return ResponseEntity.ok(serviceAdvisorDashboardService.getPendingEstimations());
    }

    @GetMapping("/active-complaints")
    public ResponseEntity<ActiveComplaintsResponseDto> getActiveComplaints() {
        return ResponseEntity.ok(serviceAdvisorDashboardService.getActiveComplaints());
    }

    @GetMapping("/status")
    public ResponseEntity<DashboardStatusResponseDto> getDashboardStatus() {
        return ResponseEntity.ok(serviceAdvisorDashboardService.getDashboardStatus());
    }
}
package com.gms.backend.hr.controller;

import com.gms.backend.hr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hr/dashboard")
@CrossOrigin("*")
public class DashboardController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private AttendanceService attendanceService;
    
    @Autowired
    private LeaveRequestService leaveRequestService;
    
    @Autowired
    private PayrollService payrollService;
    
    @Autowired
    private PerformanceReviewService performanceReviewService;
    
    @Autowired
    private GrievanceService grievanceService;
    
    @GetMapping("/overview")
    public ResponseEntity<?> getDashboardOverview() {
        try {
            Map<String, Object> dashboardData = new HashMap<>();
            
            // Employee Statistics
            Map<String, Object> employeeStats = new HashMap<>();
            employeeStats.put("totalEmployees", employeeService.getTotalEmployees());
            employeeStats.put("activeEmployees", employeeService.getActiveEmployees());
            employeeStats.put("inactiveEmployees", employeeService.getTotalEmployees() - employeeService.getActiveEmployees());
            dashboardData.put("employeeStats", employeeStats);
            
            // Attendance Statistics
            Map<String, Object> attendanceStats = new HashMap<>();
            attendanceStats.put("presentToday", attendanceService.getTodayAttendancePresent());
            attendanceStats.put("absentToday", attendanceService.getTodayAttendanceAbsent());
            dashboardData.put("attendanceStats", attendanceStats);
            
            // Leave Statistics
            Map<String, Object> leaveStats = new HashMap<>();
            leaveStats.put("pendingApprovals", leaveRequestService.getPendingLeaveApprovals());
            dashboardData.put("leaveStats", leaveStats);
            
            // Performance Statistics
            Map<String, Object> performanceStats = new HashMap<>();
            performanceStats.put("pendingReviews", performanceReviewService.getPendingReviewCount());
            dashboardData.put("performanceStats", performanceStats);
            
            // Grievance Statistics
            Map<String, Object> grievanceStats = new HashMap<>();
            grievanceStats.put("openGrievances", grievanceService.getOpenGrievancesCount());
            grievanceStats.put("highSeverityGrievances", grievanceService.getHighSeverityGrievancesCount());
            dashboardData.put("grievanceStats", grievanceStats);
            
            return new ResponseEntity<>(dashboardData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/employee-overview")
    public ResponseEntity<?> getEmployeeOverview() {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("totalEmployees", employeeService.getTotalEmployees());
            long activeCount = employeeService.getActiveEmployees();
            data.put("activeEmployees", activeCount);
            data.put("inactiveEmployees", employeeService.getTotalEmployees() - activeCount);
            data.put("status", "3 Active • 1 Probation");
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/attendance-overview")
    public ResponseEntity<?> getAttendanceOverview() {
        try {
            Map<String, Object> data = new HashMap<>();
            long present = attendanceService.getTodayAttendancePresent();
            long absent = attendanceService.getTodayAttendanceAbsent();
            long total = present + absent;
            
            double attendancePercentage = total > 0 ? (present * 100.0) / total : 0.0;
            
            data.put("presentToday", present);
            data.put("absentToday", absent);
            data.put("attendancePercentage", String.format("%.1f%%", attendancePercentage));
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/leave-overview")
    public ResponseEntity<?> getLeaveOverview() {
        try {
            Map<String, Object> data = new HashMap<>();
            long pendingCount = leaveRequestService.getPendingLeaveApprovals();
            long approvedCount = leaveRequestService.getPendingLeaveRequests().stream()
                    .filter(lr -> "APPROVED".equals(lr.getStatus()))
                    .count();
            
            data.put("pendingApprovals", pendingCount);
            data.put("approvedCount", approvedCount);
            data.put("actionNeeded", pendingCount);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/grievance-overview")
    public ResponseEntity<?> getGrievanceOverview() {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("openGrievances", grievanceService.getOpenGrievancesCount());
            data.put("highSeverityGrievances", grievanceService.getHighSeverityGrievancesCount());
            data.put("urgentCount", grievanceService.getHighSeverityGrievancesCount());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

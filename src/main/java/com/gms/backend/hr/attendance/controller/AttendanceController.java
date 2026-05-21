package com.gms.backend.hr.attendance.controller;


import com.gms.backend.hr.attendance.Service.AttendanceService;
import com.gms.backend.hr.attendance.entity.Attendance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AttendanceController {

    private final AttendanceService attendanceService;

    // 1
    @GetMapping("/summary")
    public Map<String, Object> summary() {
        return attendanceService.getSummary();
    }

    // 2
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    // 3
    @GetMapping("/{id}")
    public Attendance getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id);
    }
    // 4
    @PostMapping
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        return attendanceService.createAttendance(attendance);
    }

    // 5
    @PutMapping("/{id}")
    public Attendance updateAttendance(
            @PathVariable Long id,
            @RequestBody Attendance attendance
    ) {
        return attendanceService.updateAttendance(id, attendance);
    }

    // 6
    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        return attendanceService.deleteAttendance(id);
    }
    // 7
    @PostMapping("/check-in/{employeeId}")
    public Attendance checkIn(@PathVariable Long employeeId) {
        return attendanceService.checkIn(employeeId);
    }

    // 8
    @PostMapping("/check-out/{attendanceId}")
    public Attendance checkOut(@PathVariable Long attendanceId) {
        return attendanceService.checkOut(attendanceId);
    }

    // 9
    @GetMapping("/export")
    public List<Attendance> exportAttendance() {
        return attendanceService.exportAttendance();
    }
    // 10
    @GetMapping("/analytics")
    public Map<String, Object> analytics() {
        return attendanceService.analytics();
    }
}

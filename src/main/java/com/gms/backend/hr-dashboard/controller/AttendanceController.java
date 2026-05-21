package com.gms.backend.hr.controller;

import com.gms.backend.hr.dto.AttendanceDTO;
import com.gms.backend.hr.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hr/attendance")
@CrossOrigin("*")
public class AttendanceController {
    
    @Autowired
    private AttendanceService attendanceService;
    
    @PostMapping
    public ResponseEntity<?> recordAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        try {
            AttendanceDTO created = attendanceService.recordAttendance(attendanceDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendance(@PathVariable Long id) {
        AttendanceDTO attendance = attendanceService.getAttendanceById(id);
        if (attendance != null) {
            return new ResponseEntity<>(attendance, HttpStatus.OK);
        }
        return new ResponseEntity<>("Attendance record not found", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEmployeeAttendance(@PathVariable Long employeeId) {
        List<AttendanceDTO> attendance = attendanceService.getEmployeeAttendance(employeeId);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getAttendanceByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<AttendanceDTO> attendance = attendanceService.getAttendanceByDate(localDate);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }
    
    @GetMapping("/employee/{employeeId}/between")
    public ResponseEntity<?> getEmployeeAttendanceBetweenDates(
            @PathVariable Long employeeId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<AttendanceDTO> attendance = attendanceService.getEmployeeAttendanceBetweenDates(employeeId, start, end);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAttendance(@PathVariable Long id, @RequestBody AttendanceDTO attendanceDTO) {
        try {
            AttendanceDTO updated = attendanceService.updateAttendance(id, attendanceDTO);
            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            }
            return new ResponseEntity<>("Attendance record not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id) {
        try {
            attendanceService.deleteAttendance(id);
            return new ResponseEntity<>("Attendance record deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/today/present")
    public ResponseEntity<?> getTodayAttendancePresent() {
        long count = attendanceService.getTodayAttendancePresent();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    
    @GetMapping("/today/absent")
    public ResponseEntity<?> getTodayAttendanceAbsent() {
        long count = attendanceService.getTodayAttendanceAbsent();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}

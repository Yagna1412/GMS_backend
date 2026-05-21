package com.gms.backend.hr.attendance.repository;

import com.gms.backend.hr.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Get attendance by date
    List<Attendance> findByAttendanceDate(LocalDate date);

    // Count by status
    long countByStatus(String status);

    // Find attendance by employee and date
    Attendance findByEmployeeIdAndAttendanceDate(
            Long employeeId,
            LocalDate attendanceDate
    );

    // Latest attendance record for checkout
    Optional<Attendance> findTopByEmployeeIdAndAttendanceDateOrderByIdDesc(
            Long employeeId,
            LocalDate attendanceDate
    );

    // Employee attendance history
    List<Attendance> findByEmployeeId(Long employeeId);

    // Employee attendance by status
    List<Attendance> findByEmployeeIdAndStatus(
            Long employeeId,
            String status
    );
}
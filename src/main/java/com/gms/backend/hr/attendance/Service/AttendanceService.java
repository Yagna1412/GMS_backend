package com.gms.backend.hr.attendance.Service;

import com.gms.backend.hr.attendance.entity.Attendance;
import com.gms.backend.hr.attendance.entity.Employee;
import com.gms.backend.hr.attendance.repository.AttendanceRepository;
import com.gms.backend.hr.attendance.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    // ==============================
    // 1. Dashboard Summary
    // ==============================
    public Map<String, Object> getSummary() {

        long totalEmployees = employeeRepository.count();

        long present = attendanceRepository.countByStatus("Present");
        long absent = attendanceRepository.countByStatus("Absent");
        long late = attendanceRepository.countByStatus("Late");

        long percentage = 0;

        if (totalEmployees > 0) {
            percentage = (present * 100) / totalEmployees;
        }

        Map<String, Object> response = new HashMap<>();

        response.put("attendancePercentage", percentage);
        response.put("present", present);
        response.put("absent", absent);
        response.put("late", late);
        response.put("totalEmployees", totalEmployees);

        return response;
    }

    // ==============================
    // 2. Get All Attendance
    // ==============================
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // ==============================
    // 3. Get Attendance By ID
    // ==============================
    public Attendance getAttendanceById(Long id) {

        return attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Attendance not found with id: " + id));
    }

    // ==============================
    // 4. Create Attendance
    // ==============================
    public Attendance createAttendance(Attendance attendance) {

        if (attendance.getEmployee() == null ||
                attendance.getEmployee().getId() == null) {

            throw new RuntimeException("Employee ID is required");
        }

        Long employeeId = attendance.getEmployee().getId();

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with id: " + employeeId));

        attendance.setEmployee(employee);

        if (attendance.getAttendanceDate() == null) {
            attendance.setAttendanceDate(LocalDate.now());
        }

        return attendanceRepository.save(attendance);
    }

    // ==============================
    // 5. Update Attendance
    // ==============================
    public Attendance updateAttendance(Long id, Attendance request) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Attendance not found"));

        attendance.setAttendanceDate(request.getAttendanceDate());
        attendance.setCheckIn(request.getCheckIn());
        attendance.setCheckOut(request.getCheckOut());
        attendance.setStatus(request.getStatus());
        attendance.setRemarks(request.getRemarks());
        attendance.setSource(request.getSource());

        if (request.getEmployee() != null &&
                request.getEmployee().getId() != null) {

            Employee employee = employeeRepository.findById(
                            request.getEmployee().getId())
                    .orElseThrow(() ->
                            new RuntimeException("Employee not found"));

            attendance.setEmployee(employee);
        }

        // Calculate working minutes
        if (attendance.getCheckIn() != null &&
                attendance.getCheckOut() != null) {

            long workingMinutes = Duration.between(
                    attendance.getCheckIn(),
                    attendance.getCheckOut()
            ).toMinutes();

            attendance.setWorkingMinutes(workingMinutes);

            // Overtime after 9 hours
            if (workingMinutes > 540) {
                attendance.setOvertimeMinutes(workingMinutes - 540);
            } else {
                attendance.setOvertimeMinutes(0L);
            }
        }

        return attendanceRepository.save(attendance);
    }

    // ==============================
    // 6. Delete Attendance
    // ==============================
    public String deleteAttendance(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Attendance not found"));

        attendanceRepository.delete(attendance);

        return "Attendance deleted successfully";
    }

    // ==============================
    // 7. Employee Check In
    // ==============================
    public Attendance checkIn(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with id: " + employeeId));

        // Prevent multiple check-ins for same day
        Attendance existingAttendance =
                attendanceRepository.findByEmployeeIdAndAttendanceDate(
                        employeeId,
                        LocalDate.now()
                );

        if (existingAttendance != null) {
            throw new RuntimeException("Employee already checked in today");
        }

        LocalDateTime now = LocalDateTime.now();

        Attendance attendance = Attendance.builder()
                .employee(employee)
                .attendanceDate(LocalDate.now())
                .checkIn(now)
                .status("Present")
                .source("web")
                .workingMinutes(0L)
                .overtimeMinutes(0L)
                .lateMinutes(0L)
                .build();

        return attendanceRepository.save(attendance);
    }

    // ==============================
    // 8. Employee Check Out
    // ==============================
    public Attendance checkOut(Long employeeId) {

        Attendance attendance = attendanceRepository
                .findTopByEmployeeIdAndAttendanceDateOrderByIdDesc(
                        employeeId,
                        LocalDate.now()
                )
                .orElseThrow(() ->
                        new RuntimeException("No active attendance found")
                );

        // already checked out
        if (attendance.getCheckOut() != null) {
            return attendance;
        }

        LocalDateTime checkoutTime = LocalDateTime.now();

        attendance.setCheckOut(checkoutTime);

        long workingMinutes = Duration.between(
                attendance.getCheckIn(),
                checkoutTime
        ).toMinutes();

        attendance.setWorkingMinutes(workingMinutes);

        // overtime after 9 hours
        if (workingMinutes > 540) {
            attendance.setOvertimeMinutes(workingMinutes - 540);
        } else {
            attendance.setOvertimeMinutes(0L);
        }

        return attendanceRepository.save(attendance);
    }

    // ==============================
    // 9. Get Today's Attendance
    // ==============================
    public List<Attendance> getTodayAttendance() {

        return attendanceRepository.findByAttendanceDate(LocalDate.now());
    }

    // ==============================
    // 10. Export Attendance
    // ==============================
    public List<Attendance> exportAttendance() {

        return attendanceRepository.findAll();
    }

    // ==============================
    // 11. Attendance Analytics
    // ==============================
    public Map<String, Object> analytics() {

        long total = attendanceRepository.count();

        long present = attendanceRepository.countByStatus("Present");
        long absent = attendanceRepository.countByStatus("Absent");
        long late = attendanceRepository.countByStatus("Late");

        Map<String, Object> map = new HashMap<>();

        map.put("totalRecords", total);

        map.put(
                "presentPercentage",
                total > 0 ? (present * 100) / total : 0
        );

        map.put(
                "absentPercentage",
                total > 0 ? (absent * 100) / total : 0
        );

        map.put(
                "latePercentage",
                total > 0 ? (late * 100) / total : 0
        );

        return map;
    }
}
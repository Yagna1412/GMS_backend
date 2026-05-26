package com.gms.backend.hr.dashboard.service;

import com.gms.backend.hr.dashboard.dto.AttendanceDTO;
import com.gms.backend.hr.dashboard.entity.Attendance;
import com.gms.backend.hr.dashboard.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceService {
    
    @Autowired
    private AttendanceRepository attendanceRepository;
    
    public AttendanceDTO recordAttendance(AttendanceDTO attendanceDTO) {
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(attendanceDTO.getEmployeeId());
        attendance.setAttendanceDate(attendanceDTO.getAttendanceDate());
        attendance.setStatus(attendanceDTO.getStatus());
        attendance.setCheckInTime(attendanceDTO.getCheckInTime());
        attendance.setCheckOutTime(attendanceDTO.getCheckOutTime());
        attendance.setRemarks(attendanceDTO.getRemarks());
        
        Attendance saved = attendanceRepository.save(attendance);
        return convertToDTO(saved);
    }
    
    public AttendanceDTO getAttendanceById(Long id) {
        Optional<Attendance> attendance = attendanceRepository.findById(id);
        return attendance.map(this::convertToDTO).orElse(null);
    }
    
    public List<AttendanceDTO> getEmployeeAttendance(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<AttendanceDTO> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByAttendanceDate(date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<AttendanceDTO> getEmployeeAttendanceBetweenDates(Long employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByEmployeeIdAndAttendanceDateBetween(employeeId, startDate, endDate).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO) {
        Optional<Attendance> attendance = attendanceRepository.findById(id);
        if (attendance.isPresent()) {
            Attendance att = attendance.get();
            att.setStatus(attendanceDTO.getStatus());
            att.setCheckInTime(attendanceDTO.getCheckInTime());
            att.setCheckOutTime(attendanceDTO.getCheckOutTime());
            att.setRemarks(attendanceDTO.getRemarks());
            
            Attendance updated = attendanceRepository.save(att);
            return convertToDTO(updated);
        }
        return null;
    }
    
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
    
    public Long getTodayAttendancePresent() {
        return attendanceRepository.findByAttendanceDate(LocalDate.now()).stream()
                .filter(a -> "PRESENT".equals(a.getStatus()))
                .count();
    }
    
    public Long getTodayAttendanceAbsent() {
        return attendanceRepository.findByAttendanceDate(LocalDate.now()).stream()
                .filter(a -> "ABSENT".equals(a.getStatus()))
                .count();
    }
    
    private AttendanceDTO convertToDTO(Attendance attendance) {
        AttendanceDTO dto = new AttendanceDTO();
        dto.setId(attendance.getId());
        dto.setEmployeeId(attendance.getEmployeeId());
        dto.setAttendanceDate(attendance.getAttendanceDate());
        dto.setCheckInTime(attendance.getCheckInTime());
        dto.setCheckOutTime(attendance.getCheckOutTime());
        dto.setStatus(attendance.getStatus());
        dto.setRemarks(attendance.getRemarks());
        return dto;
    }
}

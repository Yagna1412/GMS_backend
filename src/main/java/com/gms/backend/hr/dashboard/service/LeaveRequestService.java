package com.gms.backend.hr.dashboard.service;

import com.gms.backend.hr.dashboard.dto.LeaveRequestDTO;
import com.gms.backend.hr.dashboard.entity.LeaveRequest;
import com.gms.backend.hr.dashboard.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaveRequestService {
    
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    
    public LeaveRequestDTO createLeaveRequest(LeaveRequestDTO leaveRequestDTO) {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployeeId(leaveRequestDTO.getEmployeeId());
        leaveRequest.setLeaveType(leaveRequestDTO.getLeaveType());
        leaveRequest.setStartDate(leaveRequestDTO.getStartDate());
        leaveRequest.setEndDate(leaveRequestDTO.getEndDate());
        leaveRequest.setReason(leaveRequestDTO.getReason());
        leaveRequest.setStatus("PENDING");
        
        // Calculate number of days
        long days = ChronoUnit.DAYS.between(leaveRequestDTO.getStartDate(), leaveRequestDTO.getEndDate()) + 1;
        leaveRequest.setNumberOfDays((int) days);
        
        LeaveRequest saved = leaveRequestRepository.save(leaveRequest);
        return convertToDTO(saved);
    }
    
    public LeaveRequestDTO getLeaveRequestById(Long id) {
        Optional<LeaveRequest> leaveRequest = leaveRequestRepository.findById(id);
        return leaveRequest.map(this::convertToDTO).orElse(null);
    }
    
    public List<LeaveRequestDTO> getEmployeeLeaveRequests(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<LeaveRequestDTO> getPendingLeaveRequests() {
        return leaveRequestRepository.findByStatus("PENDING").stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<LeaveRequestDTO> getApproverPendingRequests(Long approverId) {
        return leaveRequestRepository.findByStatusAndApproverId("PENDING", approverId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public LeaveRequestDTO approveLeaveRequest(Long id, Long approverId) {
        Optional<LeaveRequest> leaveRequest = leaveRequestRepository.findById(id);
        if (leaveRequest.isPresent()) {
            LeaveRequest lr = leaveRequest.get();
            lr.setStatus("APPROVED");
            lr.setApproverId(approverId);
            lr.setApprovalDate(LocalDate.now());
            
            LeaveRequest updated = leaveRequestRepository.save(lr);
            return convertToDTO(updated);
        }
        return null;
    }
    
    public LeaveRequestDTO rejectLeaveRequest(Long id, Long approverId) {
        Optional<LeaveRequest> leaveRequest = leaveRequestRepository.findById(id);
        if (leaveRequest.isPresent()) {
            LeaveRequest lr = leaveRequest.get();
            lr.setStatus("REJECTED");
            lr.setApproverId(approverId);
            lr.setApprovalDate(LocalDate.now());
            
            LeaveRequest updated = leaveRequestRepository.save(lr);
            return convertToDTO(updated);
        }
        return null;
    }
    
    public void deleteLeaveRequest(Long id) {
        leaveRequestRepository.deleteById(id);
    }
    
    public long getPendingLeaveApprovals() {
        return leaveRequestRepository.findByStatus("PENDING").size();
    }
    
    private LeaveRequestDTO convertToDTO(LeaveRequest leaveRequest) {
        LeaveRequestDTO dto = new LeaveRequestDTO();
        dto.setId(leaveRequest.getId());
        dto.setEmployeeId(leaveRequest.getEmployeeId());
        dto.setLeaveType(leaveRequest.getLeaveType());
        dto.setStartDate(leaveRequest.getStartDate());
        dto.setEndDate(leaveRequest.getEndDate());
        dto.setNumberOfDays(leaveRequest.getNumberOfDays());
        dto.setReason(leaveRequest.getReason());
        dto.setStatus(leaveRequest.getStatus());
        dto.setApproverId(leaveRequest.getApproverId());
        dto.setApprovalDate(leaveRequest.getApprovalDate());
        return dto;
    }
}

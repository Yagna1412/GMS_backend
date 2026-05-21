package com.gms.backend.hr.controller;

import com.gms.backend.hr.dto.LeaveRequestDTO;
import com.gms.backend.hr.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hr/leave-requests")
@CrossOrigin("*")
public class LeaveRequestController {
    
    @Autowired
    private LeaveRequestService leaveRequestService;
    
    @PostMapping
    public ResponseEntity<?> createLeaveRequest(@RequestBody LeaveRequestDTO leaveRequestDTO) {
        try {
            LeaveRequestDTO created = leaveRequestService.createLeaveRequest(leaveRequestDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getLeaveRequest(@PathVariable Long id) {
        LeaveRequestDTO leaveRequest = leaveRequestService.getLeaveRequestById(id);
        if (leaveRequest != null) {
            return new ResponseEntity<>(leaveRequest, HttpStatus.OK);
        }
        return new ResponseEntity<>("Leave request not found", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEmployeeLeaveRequests(@PathVariable Long employeeId) {
        List<LeaveRequestDTO> requests = leaveRequestService.getEmployeeLeaveRequests(employeeId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    
    @GetMapping("/pending")
    public ResponseEntity<?> getPendingLeaveRequests() {
        List<LeaveRequestDTO> requests = leaveRequestService.getPendingLeaveRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    
    @GetMapping("/approver/{approverId}/pending")
    public ResponseEntity<?> getApproverPendingRequests(@PathVariable Long approverId) {
        List<LeaveRequestDTO> requests = leaveRequestService.getApproverPendingRequests(approverId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approveLeaveRequest(@PathVariable Long id, @RequestParam Long approverId) {
        try {
            LeaveRequestDTO approved = leaveRequestService.approveLeaveRequest(id, approverId);
            if (approved != null) {
                return new ResponseEntity<>(approved, HttpStatus.OK);
            }
            return new ResponseEntity<>("Leave request not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}/reject")
    public ResponseEntity<?> rejectLeaveRequest(@PathVariable Long id, @RequestParam Long approverId) {
        try {
            LeaveRequestDTO rejected = leaveRequestService.rejectLeaveRequest(id, approverId);
            if (rejected != null) {
                return new ResponseEntity<>(rejected, HttpStatus.OK);
            }
            return new ResponseEntity<>("Leave request not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLeaveRequest(@PathVariable Long id) {
        try {
            leaveRequestService.deleteLeaveRequest(id);
            return new ResponseEntity<>("Leave request deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/count/pending")
    public ResponseEntity<?> getPendingLeaveApprovals() {
        long count = leaveRequestService.getPendingLeaveApprovals();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}

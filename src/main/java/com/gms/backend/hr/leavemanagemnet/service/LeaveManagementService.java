package com.gms.backend.hr.leavemanagemnet.service;

import com.gms.backend.hr.leavemanagemnet.dto.LeaveResponseDto;
import com.gms.backend.hr.leavemanagemnet.entity.LeaveRequest;
import com.gms.backend.hr.leavemanagemnet.enums.LeaveStatus;
import com.gms.backend.hr.leavemanagemnet.repository.LeaveManagementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LeaveManagementService {

    private final LeaveManagementRepository repository;

    public LeaveManagementService(
            LeaveManagementRepository repository
    ) {
        this.repository = repository;
    }

    // Get All Leaves
    public List<LeaveResponseDto> getLeaves(
            String status
    ) {

        List<LeaveRequest> leaveRequests;

        if (status == null || status.equalsIgnoreCase("ALL")) {

            leaveRequests = repository.findAll();

        } else {

            leaveRequests = repository.findByStatus(
                    LeaveStatus.valueOf(
                            status.toUpperCase()
                    )
            );
        }

        return leaveRequests.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Dashboard Stats
    public Map<String, Long> getStats() {

        Map<String, Long> stats = new HashMap<>();

        stats.put(
                "pending",
                repository.countByStatus(
                        LeaveStatus.PENDING
                )
        );

        stats.put(
                "approved",
                repository.countByStatus(
                        LeaveStatus.APPROVED
                )
        );

        stats.put(
                "rejected",
                repository.countByStatus(
                        LeaveStatus.REJECTED
                )
        );

        stats.put(
                "totalRequests",
                repository.count()
        );

        return stats;
    }

    // Approve Leave
    public String approveLeave(
            Long id
    ) {

        LeaveRequest leave = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave Request Not Found"
                        ));

        leave.setStatus(
                LeaveStatus.APPROVED
        );

        leave.setApprovalDate(
                LocalDate.now()
        );

        repository.save(leave);

        return "Leave Approved Successfully";
    }

    // Reject Leave
    public String rejectLeave(
            Long id
    ) {

        LeaveRequest leave = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave Request Not Found"
                        ));

        leave.setStatus(
                LeaveStatus.REJECTED
        );

        leave.setApprovalDate(
                LocalDate.now()
        );

        repository.save(leave);

        return "Leave Rejected Successfully";
    }

    // DTO Mapping
    private LeaveResponseDto mapToDto(
            LeaveRequest leave
    ) {

        return new LeaveResponseDto(

                leave.getId(),

                leave.getStaffId(),

                leave.getLeaveType(),

                leave.getFromDate(),

                leave.getToDate(),

                leave.getDays(),

                leave.getReason(),

                leave.getStatus()
        );
    }
}
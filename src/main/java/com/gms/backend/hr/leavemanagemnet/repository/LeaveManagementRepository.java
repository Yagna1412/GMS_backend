package com.gms.backend.hr.leavemanagemnet.repository;

import com.gms.backend.hr.leavemanagemnet.entity.LeaveRequest;
import com.gms.backend.hr.leavemanagemnet.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveManagementRepository
        extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByStatus(
            LeaveStatus status
    );

    long countByStatus(
            LeaveStatus status
    );
}
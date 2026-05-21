package com.gms.backend.hr.repository;

import com.gms.backend.hr.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);
    List<LeaveRequest> findByStatus(String status);
    List<LeaveRequest> findByApproverId(Long approverId);
    List<LeaveRequest> findByEmployeeIdAndStartDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);
    List<LeaveRequest> findByStatusAndApproverId(String status, Long approverId);
}

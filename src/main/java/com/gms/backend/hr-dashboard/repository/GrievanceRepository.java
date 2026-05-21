package com.gms.backend.hr.repository;

import com.gms.backend.hr.entity.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
    List<Grievance> findByEmployeeId(Long employeeId);
    List<Grievance> findByStatus(String status);
    List<Grievance> findBySeverity(String severity);
    List<Grievance> findByAssignedTo(Long assignedTo);
    List<Grievance> findByStatusAndSeverity(String status, String severity);
}

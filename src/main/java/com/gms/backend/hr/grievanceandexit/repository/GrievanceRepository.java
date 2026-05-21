package com.gms.backend.hr.grievanceandexit.repository;

import com.gms.backend.hr.grievanceandexit.entity.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrievanceRepository extends JpaRepository<Grievance, Long> {



        List<Grievance> findByStatus(String status);

        long countByStatus(String status);

        long countBySeverity(String severity);
}

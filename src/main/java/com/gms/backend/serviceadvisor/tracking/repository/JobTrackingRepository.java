package com.gms.backend.serviceadvisor.tracking.repository;

import com.gms.backend.serviceadvisor.tracking.entity.JobTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobTrackingRepository extends JpaRepository<JobTracking, Long> {

    List<JobTracking> findByJobCardId(Long jobCardId);

    // ✅ NEW
    List<JobTracking> findByStatus(String status);
}
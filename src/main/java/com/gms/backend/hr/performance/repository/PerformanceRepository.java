package com.gms.backend.hr.performance.repository;

import com.gms.backend.hr.performance.entity.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository
        extends JpaRepository<PerformanceReview, Long> {

    long countByStatus(String status);
}
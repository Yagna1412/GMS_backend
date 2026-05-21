package com.gms.backend.hr.repository;

import com.gms.backend.hr.entity.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {
    List<PerformanceReview> findByEmployeeId(Long employeeId);
    List<PerformanceReview> findByReviewerId(Long reviewerId);
    List<PerformanceReview> findByStatus(String status);
    List<PerformanceReview> findByEmployeeIdAndReviewDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);
    List<PerformanceReview> findByReviewerIdAndStatus(Long reviewerId, String status);
}

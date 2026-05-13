package com.gms.backend.serviceadvisor.estimation.repository;

import com.gms.backend.serviceadvisor.estimation.dto.EstimationSummaryDTO;
import com.gms.backend.serviceadvisor.estimation.entity.Estimation;
import com.gms.backend.serviceadvisor.estimation.entity.Estimation.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstimationRepository extends JpaRepository<Estimation, Long> {

    Optional<Estimation> findByEstimationId(String estimationId);

    List<Estimation> findAllByOrderByCreatedAtDesc();

    List<Estimation> findByStatusOrderByCreatedAtDesc(Status status);

    List<Estimation> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

    // Summary Query
    @Query("""
        SELECT new com.gms.backend.serviceadvisor.estimation.dto.EstimationSummaryDTO(
            COUNT(CASE WHEN e.status = 'PENDING' THEN 1 END),
            COUNT(CASE WHEN e.status = 'APPROVED' THEN 1 END),
            COALESCE(SUM(e.amount), 0)
        )
        FROM Estimation e
    """)
    EstimationSummaryDTO getSummary();

    // Latest Estimation ID
    @Query(value = """
        SELECT estimation_id
        FROM estimations
        WHERE estimation_id LIKE CONCAT(:prefix, '%')
        ORDER BY id DESC
        LIMIT 1
    """, nativeQuery = true)
    Optional<String> findLatestEstimationIdForYear(
            @Param("prefix") String prefix
    );
}
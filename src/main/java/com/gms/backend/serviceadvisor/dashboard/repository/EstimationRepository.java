package com.gms.backend.serviceadvisor.dashboard.repository;


import com.gms.backend.serviceadvisor.dashboard.entity.Estimation;
import com.gms.backend.serviceadvisor.dashboard.enums.EstimationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EstimationRepository extends JpaRepository<Estimation, Long> {

    long countByStatus(EstimationStatus status);

    List<Estimation> findByStatusOrderByCreatedDateDesc(EstimationStatus status);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Estimation e WHERE e.status = :status")
    BigDecimal sumAmountByStatus(@Param("status") EstimationStatus status);
}

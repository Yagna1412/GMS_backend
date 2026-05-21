package com.gms.backend.inventory.dashboard.repository;

import com.gms.backend.inventory.dashboard.entity.StockAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockAuditRepository extends JpaRepository<StockAudit, Long> {

    Long countByStatus(String status);
}

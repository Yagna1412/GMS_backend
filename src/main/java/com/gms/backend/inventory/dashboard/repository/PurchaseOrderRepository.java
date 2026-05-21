package com.gms.backend.inventory.dashboard.repository;

import com.gms.backend.inventory.dashboard.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    Long countByStatus(String status);
}

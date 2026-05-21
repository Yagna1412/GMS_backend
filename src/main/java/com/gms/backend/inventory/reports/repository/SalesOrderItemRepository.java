package com.gms.backend.inventory.reports.repository;

import com.gms.backend.inventory.reports.entity.SalesOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderItemRepository
        extends JpaRepository<SalesOrderItem, Long> {
}
package com.gms.backend.inventory.reports.repository;

import com.gms.backend.inventory.reports.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository
        extends JpaRepository<SalesOrder, Long> {
}
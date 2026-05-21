package com.gms.backend.inventory.reports.repository;

import com.gms.backend.inventory.reports.entity.InventoryStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryStockRepository
        extends JpaRepository<InventoryStock, Long> {
}
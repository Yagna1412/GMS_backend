package com.gms.backend.inventory.reports.repository;

import com.gms.backend.inventory.reports.entity.StockBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockBatchRepository
        extends JpaRepository<StockBatch, Long> {
}
package com.gms.backend.inventory.reports.repository;

import com.gms.backend.inventory.reports.entity.ExportLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportLogRepository
        extends JpaRepository<ExportLog, Long> {
}
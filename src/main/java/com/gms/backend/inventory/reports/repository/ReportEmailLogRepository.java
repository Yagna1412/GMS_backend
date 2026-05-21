package com.gms.backend.inventory.reports.repository;

import com.gms.backend.inventory.reports.entity.ReportEmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportEmailLogRepository
        extends JpaRepository<ReportEmailLog, Long> {
}
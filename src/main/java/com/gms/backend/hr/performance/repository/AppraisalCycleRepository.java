package com.gms.backend.hr.performance.repository;

import com.gms.backend.hr.performance.entity.AppraisalCycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppraisalCycleRepository
        extends JpaRepository<AppraisalCycle, Long> {
}
package com.gms.backend.technician.qc.repository;

import com.gms.backend.technician.qc.entity.QCChecklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QCChecklistRepository
        extends JpaRepository<QCChecklist, Long> {

    // Find checklist using job card id
    QCChecklist findByJobCardId(Long jobCardId);

    // Delete old checklist
    void deleteByJobCardId(Long jobCardId);

    // Dashboard count
    long countByStatus(String status);
}
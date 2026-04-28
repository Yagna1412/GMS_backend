package com.gms.backend.technician.partsrequest.repository;

import com.gms.backend.technician.partsrequest.entity.AllocatedPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AllocatedPartRepo extends JpaRepository<AllocatedPart, Long> {

    // ✔ Get all parts by jobCardId (NO sorting)
    List<AllocatedPart> findByJobCardId(String jobCardId);

    // ✔ Distinct job card IDs
    @Query("SELECT DISTINCT a.jobCardId FROM AllocatedPart a ORDER BY a.jobCardId")
    List<String> findDistinctJobCardIds();
}
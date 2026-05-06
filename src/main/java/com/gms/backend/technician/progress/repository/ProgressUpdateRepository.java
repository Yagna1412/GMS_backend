package com.gms.backend.technician.progress.repository;

import com.gms.backend.technician.progress.entity.ProgressUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgressUpdateRepository extends JpaRepository<ProgressUpdate, Long> {

    // EXISTING
    List<ProgressUpdate> findByJobIdOrderByCreatedAtDesc(String jobId);

    //  NEW - ACTIVE JOBS
    @Query("SELECT DISTINCT p.jobId FROM ProgressUpdate p")
    List<String> findDistinctJobIds();
}
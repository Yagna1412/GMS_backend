package com.gms.backend.serviceadvisor.tracking.repository;

import com.gms.backend.serviceadvisor.tracking.entity.JobTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobTrackingRepository extends JpaRepository<JobTracking, Long> {

    // =========================
    // FIND BY JOB CARD ID
    // =========================
    List<JobTracking> findByJobCardId(Long jobCardId);

    // =========================
    // FIND BY STATUS
    // =========================
    List<JobTracking> findByStatus(String status);

    // =========================
    // GET LATEST RECORD
    // =========================
    Optional<JobTracking> findTopByJobCardIdOrderByUpdatedAtDesc(Long jobCardId);

    // =========================
    // DELETE BY JOB CARD ID
    // =========================
    @Transactional
    @Modifying
    @Query("DELETE FROM JobTracking j WHERE j.jobCardId = :jobCardId")
    void deleteByJobCardId(Long jobCardId);
}
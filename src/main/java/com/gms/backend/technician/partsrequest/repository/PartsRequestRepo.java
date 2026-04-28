package com.gms.backend.technician.partsrequest.repository;

import com.gms.backend.technician.partsrequest.entity.PartsRequest;
import com.gms.backend.technician.partsrequest.entity.PartsRequest.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PartsRequestRepo extends JpaRepository<PartsRequest, Long> {

    // ── Summary: counts for Pending / Approved / Received ──────────────────
    // Returns Object[] — index 0=pending, 1=approved, 2=received
    @Query("""
        SELECT
            SUM(CASE WHEN p.status = 'PENDING'  THEN 1 ELSE 0 END),
            SUM(CASE WHEN p.status = 'APPROVED' THEN 1 ELSE 0 END),
            SUM(CASE WHEN p.status = 'RECEIVED' THEN 1 ELSE 0 END)
        FROM PartsRequest p
    """)
    Object[] getSummary();

    // ── Sequential ID generation: find max requestId for current year ───────
    // e.g. prefix = "PR/MUM/2026/%"  →  returns "PR/MUM/2026/0047"
    @Query(value = """
        SELECT request_id FROM parts_requests
        WHERE request_id LIKE :prefix
        ORDER BY id DESC
        LIMIT 1
    """, nativeQuery = true)
    Optional<String> findMaxRequestIdForYear(@Param("prefix") String prefix);

    // ── Active job card IDs for the modal dropdown ──────────────────────────
    // Excludes RECEIVED and REJECTED requests
    @Query("""
        SELECT DISTINCT p.jobCardId FROM PartsRequest p
        WHERE p.status NOT IN ('RECEIVED', 'REJECTED')
        ORDER BY p.jobCardId
    """)
    List<String> findDistinctActiveJobCardIds();

    // ── Filter by status ────────────────────────────────────────────────────
    List<PartsRequest> findByStatus(Status status);

    // ── Filter by job card ──────────────────────────────────────────────────
    List<PartsRequest> findByJobCardIdOrderByRequestedAtDesc(String jobCardId);
}
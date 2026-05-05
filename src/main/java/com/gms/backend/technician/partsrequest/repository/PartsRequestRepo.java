package com.gms.backend.technician.partsrequest.repository;

import com.gms.backend.technician.partsrequest.entity.PartsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PartsRequestRepo extends JpaRepository<PartsRequest, Long> {

    // Summary: Pending / Approved / Received counts
    @Query(value = """
        SELECT
            SUM(CASE WHEN status = 'PENDING'  THEN 1 ELSE 0 END),
            SUM(CASE WHEN status = 'APPROVED' THEN 1 ELSE 0 END),
            SUM(CASE WHEN status = 'RECEIVED' THEN 1 ELSE 0 END)
        FROM parts_requests
    """, nativeQuery = true)
    Object[] getSummary();

    // Sequential ID generation: PR/MUM/2026/0048
    @Query(value = """
        SELECT request_id FROM parts_requests
        WHERE request_id LIKE :prefix
        ORDER BY id DESC
        LIMIT 1
    """, nativeQuery = true)
    Optional<String> findMaxRequestIdForYear(@Param("prefix") String prefix);

    // Get all active job card IDs for dropdown
    @Query("""
        SELECT DISTINCT p.jobCardId FROM PartsRequest p 
        WHERE p.status NOT IN ('RECEIVED', 'REJECTED')
        ORDER BY p.jobCardId
    """)
    List<String> findDistinctActiveJobCardIds();
}
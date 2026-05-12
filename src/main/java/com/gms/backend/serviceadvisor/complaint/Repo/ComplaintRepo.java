package com.gms.backend.serviceadvisor.complaint.Repo;

import com.gms.backend.serviceadvisor.complaint.Entity.ComplaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ComplaintRepo extends JpaRepository<ComplaintEntity, Long> {

    Optional<ComplaintEntity> findByComplaintId(String complaintId);

    boolean existsByComplaintId(String complaintId);

    long countByStatus(String status);
}
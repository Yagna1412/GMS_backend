package com.gms.backend.technician.qc.repository;


import com.gms.backend.technician.qc.entity.JobCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobCardRepository extends JpaRepository<JobCard, Long> {

    List<JobCard> findByStatus(String status);

    long countByStatus(String status);
}

package com.gms.backend.serviceadvisor.dashboard.repository;

import com.gms.backend.serviceadvisor.dashboard.entity.JobCard;
import com.gms.backend.serviceadvisor.dashboard.enums.JobCardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface JobCardRepository extends JpaRepository<JobCard, Long> {

    long countByStatus(JobCardStatus status);

    long countByCreatedOnBetween(LocalDateTime start, LocalDateTime end);
}
package com.gms.backend.serviceadvisor.jobcard.repository;

import com.gms.backend.serviceadvisor.jobcard.entity.JobCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobCardRepository extends JpaRepository<JobCardEntity, Long> {
    Optional<JobCardEntity> findTopByOrderByIdDesc();
}
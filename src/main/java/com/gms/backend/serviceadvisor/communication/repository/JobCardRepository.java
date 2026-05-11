package com.gms.backend.serviceadvisor.communication.repository;


import com.gms.backend.serviceadvisor.communication.entity.JobCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCardRepository extends JpaRepository<JobCardEntity, Long> {
}
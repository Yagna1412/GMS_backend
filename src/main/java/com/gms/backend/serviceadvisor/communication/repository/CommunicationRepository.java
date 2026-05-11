package com.gms.backend.serviceadvisor.communication.repository;


import com.gms.backend.serviceadvisor.communication.entity.CommunicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface CommunicationRepository extends JpaRepository<CommunicationEntity, Long> {

    long countByCreatedAtAfter(LocalDateTime time);

    long countByTypeAndCreatedAtAfter(String type, LocalDateTime time);

    List<CommunicationEntity> findAllByOrderByCreatedAtDesc();
}
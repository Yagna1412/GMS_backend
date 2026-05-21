package com.gms.backend.hr.training.repository;

import com.gms.backend.hr.training.entity.TrainingParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<TrainingParticipant, Long> {

    // Powers Required API Endpoint #9 (Safely drops a registration entry bound to a specific program context)
    Optional<TrainingParticipant> findByTrainingProgramIdAndId(Long trainingProgramId, Long id);
}
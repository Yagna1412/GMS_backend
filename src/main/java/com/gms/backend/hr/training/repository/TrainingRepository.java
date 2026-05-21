package com.gms.backend.hr.training.repository;

import com.gms.backend.hr.training.entity.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingProgram, Long> {

    // Powers Required API Endpoint #6 (Get programs by status)
    List<TrainingProgram> findByStatus(String status);

    // Powers Required API Endpoint #7 (Get programs by type)
    List<TrainingProgram> findByTrainingType(String trainingType);
}
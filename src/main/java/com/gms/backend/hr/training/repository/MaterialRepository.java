package com.gms.backend.hr.training.repository;

import com.gms.backend.hr.training.entity.TrainingMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<TrainingMaterial, Long> {

    // 👈 ADD THIS METHOD TO FIX THE MATERIAL COMPILATION ERROR
    List<TrainingMaterial> findByTrainingProgramId(Long trainingProgramId);
}
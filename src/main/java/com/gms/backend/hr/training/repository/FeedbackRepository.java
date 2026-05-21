package com.gms.backend.hr.training.repository;

import com.gms.backend.hr.training.entity.TrainingFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<TrainingFeedback, Long> {

    // 👈 ADD THIS METHOD TO FIX THE FEEDBACK COMPILATION ERROR
    List<TrainingFeedback> findByTrainingProgramId(Long trainingProgramId);
}
package com.gms.backend.hr.training.service;

import com.gms.backend.hr.training.dto.TrainerDTO; // 👈 Make sure this is imported
import com.gms.backend.hr.training.entity.Trainer;
import java.util.List;

public interface TrainerService {

    // 👈 FIXED: Changed from Trainer to TrainerDTO
    Trainer createTrainer(TrainerDTO dto);

    List<Trainer> getAllTrainers();

    Trainer getTrainerById(Long id);

    // 👈 FIXED: Changed from Trainer to TrainerDTO
    Trainer updateTrainer(Long id, TrainerDTO dto);

    void deleteTrainer(Long id);
}
package com.gms.backend.hr.training.service.impl;

import com.gms.backend.hr.training.dto.TrainerDTO;
import com.gms.backend.hr.training.entity.Trainer;
import com.gms.backend.hr.training.exception.ResourceNotFoundException;
import com.gms.backend.hr.training.repository.TrainerRepository;
import com.gms.backend.hr.training.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    @Override
    public Trainer createTrainer(TrainerDTO dto) { // 👈 Parameter matches interface exactly
        Trainer trainer = new Trainer();
        trainer.setTrainerName(dto.getTrainerName());
        trainer.setTrainerType(dto.getTrainerType());
        trainer.setOrganization(dto.getOrganization());
        trainer.setEmail(dto.getEmail());
        trainer.setPhone(dto.getPhone());
        trainer.setSpecialization(dto.getSpecialization());

        return trainerRepository.save(trainer);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer Not Found with ID: " + id));
    }

    @Override
    public Trainer updateTrainer(Long id, TrainerDTO dto) { // 👈 Parameter matches interface exactly
        Trainer existingTrainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer Not Found with ID: " + id));

        existingTrainer.setTrainerName(dto.getTrainerName());
        existingTrainer.setTrainerType(dto.getTrainerType());
        existingTrainer.setOrganization(dto.getOrganization());
        existingTrainer.setEmail(dto.getEmail());
        existingTrainer.setPhone(dto.getPhone());
        existingTrainer.setSpecialization(dto.getSpecialization());

        return trainerRepository.save(existingTrainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer Not Found with ID: " + id));
        trainerRepository.delete(trainer);
    }
}
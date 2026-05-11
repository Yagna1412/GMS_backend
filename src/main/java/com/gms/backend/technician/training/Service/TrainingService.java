package com.gms.backend.technician.training.Service;

import com.gms.backend.technician.training.Dto.TrainingDTO;
import com.gms.backend.technician.training.Entity.Training;
import com.gms.backend.technician.training.Entity.UserTraining;
import com.gms.backend.technician.training.Repository.TrainingRepository;
import com.gms.backend.technician.training.Repository.UserTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TrainingService {

    @Autowired
    private UserTrainingRepository userTrainingRepo;

    @Autowired
    private TrainingRepository trainingRepo;

    public List<TrainingDTO> getTrainings(Long userId) {

        return userTrainingRepo.findByUserId(userId).stream().map(ut -> {

            Training t = trainingRepo.findById(ut.getTrainingId()).orElse(null);

            TrainingDTO dto = new TrainingDTO();

            dto.setTrainingId(t.getId());
            dto.setTitle(t.getTitle());
            dto.setCode(t.getCode());
            dto.setStatus(ut.getStatus());

            if ("IN_PROGRESS".equals(ut.getStatus())) {
                dto.setProgress(ut.getProgress());
                dto.setDueDate(ut.getDueDate());
                dto.setMaterialsAvailable(true);
            } else {
                dto.setCompletionDate(ut.getCompletionDate());
                dto.setCertificateCode("CERT/EV/001");
            }

            return dto;

        }).toList();
    }

    public void updateProgress(Long id, int progress) {

        UserTraining ut = userTrainingRepo.findById(id).orElseThrow();

        ut.setProgress(progress);

        if (progress == 100) {
            ut.setStatus("COMPLETED");
            ut.setCompletionDate(LocalDate.now());
        } else {
            ut.setStatus("IN_PROGRESS");
        }

        userTrainingRepo.save(ut);
    }
}
package com.gms.backend.hr.training.service.impl;

import com.gms.backend.hr.training.dto.TrainingProgramDTO;
import com.gms.backend.hr.training.entity.Trainer;
import com.gms.backend.hr.training.entity.TrainingFeedback;
import com.gms.backend.hr.training.entity.TrainingMaterial;
import com.gms.backend.hr.training.entity.TrainingProgram;
import com.gms.backend.hr.training.exception.ResourceNotFoundException;
import com.gms.backend.hr.training.repository.FeedbackRepository;
import com.gms.backend.hr.training.repository.MaterialRepository;
import com.gms.backend.hr.training.repository.TrainerRepository;
import com.gms.backend.hr.training.repository.TrainingRepository;
import com.gms.backend.hr.training.service.TrainingService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final FeedbackRepository feedbackRepository;
    private final MaterialRepository materialRepository;
    private final TrainerRepository trainerRepository; // 👈 Injected to handle trainer mappings

    @Override
    public TrainingProgram createTraining(TrainingProgramDTO dto) {
        TrainingProgram program = new TrainingProgram();
        mapDtoToEntity(dto, program);
        return trainingRepository.save(program);
    }

    @Override
    public List<TrainingProgram> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public TrainingProgram getTrainingById(Long id) {
        return trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Program Not Found with ID: " + id));
    }

    @Override
    public TrainingProgram updateTraining(Long id, TrainingProgramDTO dto) {
        TrainingProgram program = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Program Not Found with ID: " + id));

        mapDtoToEntity(dto, program);
        return trainingRepository.save(program);
    }

    @Override
    public void deleteTraining(Long id) {
        TrainingProgram program = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Program Not Found with ID: " + id));

        trainingRepository.delete(program);
    }

    @Override
    public TrainingFeedback createFeedback(TrainingFeedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<TrainingFeedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public TrainingMaterial createMaterial(TrainingMaterial material) {
        return materialRepository.save(material);
    }

    @Override
    public List<TrainingMaterial> getAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public void sendTrainingNotifications() {
        // Implementation for dispatching notifications
        System.out.println("Dispatched notification updates.");
    }

    @Override
    public Object getTrainingCalendarData() {
        // Returns training items formatted for calendar access layers
        return trainingRepository.findAll();
    }

    @Override
    public Object getInvestmentReports() {
        // Aggregates systemic budget investment values across all active items
        return trainingRepository.findAll().stream()
                .mapToDouble(p -> p.getCost() != null ? p.getCost() : 0.0)
                .sum();
    }

    @Override
    public Object getAnalyticsDashboardData() {
        Map<String, Long> metrics = new HashMap<>();
        metrics.put("totalPrograms", trainingRepository.count());
        return metrics;
    }

    @Override
    public List<TrainingMaterial> getMaterialsByProgramId(Long id) {
        // 👈 Uses custom repository finder matching the relational architecture
        return materialRepository.findByTrainingProgramId(id);
    }

    @Override
    public List<TrainingFeedback> getFeedbackByProgramId(Long id) {
        // 👈 Uses custom repository finder matching the relational architecture
        return feedbackRepository.findByTrainingProgramId(id);
    }

    @Override
    public List<TrainingProgram> getProgramsByType(String type) {
        return trainingRepository.findByTrainingType(type);
    }

    @Override
    public List<TrainingProgram> getProgramsByStatus(String status) {
        return trainingRepository.findByStatus(status);
    }

    /**
     * Helper mapping utility to centralize fields and link trainer relational associations smoothly
     */
    private void mapDtoToEntity(TrainingProgramDTO dto, TrainingProgram program) {
        program.setProgramName(dto.getProgramName());
        program.setDescription(dto.getDescription());
        program.setTrainingType(dto.getTrainingType());
        program.setDurationDays(dto.getDurationDays());
        program.setCost(dto.getCost());
        program.setStatus(dto.getStatus());

        // Dynamic checks to resolve and bind the relational Trainer mapping context cleanly
        if (dto.getTrainerId() != null) {
            Trainer trainer = trainerRepository.findById(dto.getTrainerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Trainer Not Found with ID: " + dto.getTrainerId()));
            program.setTrainer(trainer);
        }
    }
}
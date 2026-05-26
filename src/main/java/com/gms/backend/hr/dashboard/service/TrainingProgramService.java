package com.gms.backend.hr.dashboard.service;

import com.gms.backend.hr.dashboard.dto.TrainingProgramDTO;
import com.gms.backend.hr.dashboard.entity.TrainingProgram;
import com.gms.backend.hr.dashboard.repository.TrainingProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingProgramService {
    
    @Autowired
    private TrainingProgramRepository trainingProgramRepository;
    
    public TrainingProgramDTO createTrainingProgram(TrainingProgramDTO trainingProgramDTO) {
        TrainingProgram program = new TrainingProgram();
        program.setProgramName(trainingProgramDTO.getProgramName());
        program.setStartDate(trainingProgramDTO.getStartDate());
        program.setEndDate(trainingProgramDTO.getEndDate());
        program.setTrainer(trainingProgramDTO.getTrainer());
        program.setLocation(trainingProgramDTO.getLocation());
        program.setDescription(trainingProgramDTO.getDescription());
        program.setCapacity(trainingProgramDTO.getCapacity());
        program.setStatus("SCHEDULED");
        
        TrainingProgram saved = trainingProgramRepository.save(program);
        return convertToDTO(saved);
    }
    
    public TrainingProgramDTO getTrainingProgramById(Long id) {
        Optional<TrainingProgram> program = trainingProgramRepository.findById(id);
        return program.map(this::convertToDTO).orElse(null);
    }
    
    public List<TrainingProgramDTO> getAllTrainingPrograms() {
        return trainingProgramRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<TrainingProgramDTO> getScheduledTrainings() {
        return trainingProgramRepository.findByStatus("SCHEDULED").stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<TrainingProgramDTO> getOngoingTrainings() {
        return trainingProgramRepository.findByStatus("ONGOING").stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public TrainingProgramDTO updateTrainingProgram(Long id, TrainingProgramDTO trainingProgramDTO) {
        Optional<TrainingProgram> program = trainingProgramRepository.findById(id);
        if (program.isPresent()) {
            TrainingProgram tp = program.get();
            tp.setProgramName(trainingProgramDTO.getProgramName());
            tp.setTrainer(trainingProgramDTO.getTrainer());
            tp.setLocation(trainingProgramDTO.getLocation());
            tp.setDescription(trainingProgramDTO.getDescription());
            tp.setStatus(trainingProgramDTO.getStatus());
            tp.setCapacity(trainingProgramDTO.getCapacity());
            
            TrainingProgram updated = trainingProgramRepository.save(tp);
            return convertToDTO(updated);
        }
        return null;
    }
    
    public void deleteTrainingProgram(Long id) {
        trainingProgramRepository.deleteById(id);
    }
    
    private TrainingProgramDTO convertToDTO(TrainingProgram program) {
        TrainingProgramDTO dto = new TrainingProgramDTO();
        dto.setId(program.getId());
        dto.setProgramName(program.getProgramName());
        dto.setStartDate(program.getStartDate());
        dto.setEndDate(program.getEndDate());
        dto.setTrainer(program.getTrainer());
        dto.setLocation(program.getLocation());
        dto.setDescription(program.getDescription());
        dto.setStatus(program.getStatus());
        dto.setCapacity(program.getCapacity());
        return dto;
    }
}

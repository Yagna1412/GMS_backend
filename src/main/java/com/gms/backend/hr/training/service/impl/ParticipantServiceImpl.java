package com.gms.backend.hr.training.service.impl;

import com.gms.backend.hr.training.dto.ParticipantDto;
import com.gms.backend.hr.training.entity.Staff;
import com.gms.backend.hr.training.entity.TrainingParticipant;
import com.gms.backend.hr.training.entity.TrainingProgram;
import com.gms.backend.hr.training.exception.ResourceNotFoundException;
import com.gms.backend.hr.training.repository.ParticipantRepository;
import com.gms.backend.hr.training.repository.StaffRepository;
import com.gms.backend.hr.training.repository.TrainingRepository;
import com.gms.backend.hr.training.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final StaffRepository staffRepository;
    private final TrainingRepository trainingRepository;

    @Override
    public TrainingParticipant createParticipant(Long trainingProgramId, ParticipantDto dto) {
        TrainingProgram program = trainingRepository.findById(trainingProgramId)
                .orElseThrow(() -> new ResourceNotFoundException("Training program not found with ID: " + trainingProgramId));

        Staff staff = staffRepository.findById(dto.getStaffId())
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + dto.getStaffId()));

        TrainingParticipant participant = new TrainingParticipant();
        participant.setTrainingProgram(program);
        participant.setStaff(staff);

        participant.setEmployeeId(dto.getEmployeeId());
        participant.setAttendanceStatus(dto.getAttendanceStatus());
        participant.setCompletionStatus(dto.getCompletionStatus());
        participant.setFeedbackRating(dto.getFeedbackRating());
        participant.setAttendancePercentage(dto.getAttendancePercentage());
        participant.setTrainingId(dto.getTrainingId());

        return participantRepository.save(participant);
    }

    @Override
    public List<TrainingParticipant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public TrainingParticipant getParticipantById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with ID: " + id));
    }

    @Override
    public TrainingParticipant updateParticipant(Long id, ParticipantDto dto) {
        TrainingParticipant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with ID: " + id));

        // Use the incoming DTO tracking context fields to re-verify any shifts in program or staff mappings
        TrainingProgram program = trainingRepository.findById(dto.getTrainingProgramId())
                .orElseThrow(() -> new ResourceNotFoundException("Training program not found with ID: " + dto.getTrainingProgramId()));

        Staff staff = staffRepository.findById(dto.getStaffId())
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + dto.getStaffId()));

        participant.setTrainingProgram(program);
        participant.setStaff(staff);
        participant.setEmployeeId(dto.getEmployeeId());
        participant.setAttendanceStatus(dto.getAttendanceStatus());
        participant.setCompletionStatus(dto.getCompletionStatus());
        participant.setFeedbackRating(dto.getFeedbackRating());
        participant.setAttendancePercentage(dto.getAttendancePercentage());
        participant.setTrainingId(dto.getTrainingId());

        return participantRepository.save(participant);
    }

    @Override
    public void deleteParticipant(Long id) {
        TrainingParticipant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with ID: " + id));
        participantRepository.delete(participant);
    }

    @Override
    public void deleteParticipantFromProgram(Long trainingProgramId, Long participantId) {
        TrainingParticipant participant = participantRepository.findByTrainingProgramIdAndId(trainingProgramId, participantId)
                .orElseThrow(() -> new ResourceNotFoundException("Participant registration context not found for Program: " + trainingProgramId + " and Participant ID: " + participantId));
        participantRepository.delete(participant);
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }
}
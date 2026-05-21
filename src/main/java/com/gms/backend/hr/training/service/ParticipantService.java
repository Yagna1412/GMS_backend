package com.gms.backend.hr.training.service;

import com.gms.backend.hr.training.dto.ParticipantDto;
import com.gms.backend.hr.training.entity.Staff;
import com.gms.backend.hr.training.entity.TrainingParticipant;

import java.util.List;

public interface ParticipantService {

    // 👈 FIXED: Added trainingProgramId to match Controller context routes and Implementation logic
    TrainingParticipant createParticipant(Long trainingProgramId, ParticipantDto dto);

    List<TrainingParticipant> getAllParticipants();

    @Deprecated
    TrainingParticipant getParticipantById(Long id);

    TrainingParticipant updateParticipant(Long id, ParticipantDto dto);

    @Deprecated
    void deleteParticipant(Long id);

    List<Staff> getAllStaff();

    Staff createStaff(Staff staff);

    // Powers contextual endpoint #9 (Safely drops participant registration out of a specific program)
    void deleteParticipantFromProgram(Long trainingProgramId, Long participantId);
}
package com.gms.backend.hr.training.controller;

import com.gms.backend.hr.training.dto.ParticipantDto;
import com.gms.backend.hr.training.entity.Staff;
import com.gms.backend.hr.training.entity.TrainingParticipant;
import com.gms.backend.hr.training.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    // 8. Assign participants to a specific program context
    @PostMapping("/training-programs/{id}/participants")
    public TrainingParticipant assignParticipant(@PathVariable("id") Long trainingProgramId, @RequestBody ParticipantDto dto) {
        return participantService.createParticipant(trainingProgramId, dto);
    }

    // 9. Remove participant from a specific program context
    @DeleteMapping("/training-programs/{id}/participants/{participantId}")
    public void removeParticipant(@PathVariable("id") Long trainingProgramId, @PathVariable("participantId") Long participantId) {
        participantService.deleteParticipantFromProgram(trainingProgramId, participantId);
    }

    // Context-driven performance tracking metric updates
    @PutMapping("/training-programs/{id}/participants/{participantId}")
    public TrainingParticipant updateParticipant(
            @PathVariable("id") Long trainingProgramId,
            @PathVariable("participantId") Long participantId,
            @RequestBody ParticipantDto dto) {
        return participantService.updateParticipant(participantId, dto);
    }

    // Administrative baseline CRUD backup utilities
    @GetMapping("/participants")
    public List<TrainingParticipant> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/participants/{id}")
    public TrainingParticipant getParticipantById(@PathVariable Long id) {
        return participantService.getParticipantById(id);
    }

    @PostMapping("/staff")
    public Staff createStaff(@RequestBody Staff staff) {
        return participantService.createStaff(staff);
    }

    @GetMapping("/staff")
    public List<Staff> getAllStaff() {
        return participantService.getAllStaff();
    }
}
package com.gms.backend.hr.controller;

import com.gms.backend.hr.dto.TrainingProgramDTO;
import com.gms.backend.hr.service.TrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hr/training-programs")
@CrossOrigin("*")
public class TrainingProgramController {
    
    @Autowired
    private TrainingProgramService trainingProgramService;
    
    @PostMapping
    public ResponseEntity<?> createTrainingProgram(@RequestBody TrainingProgramDTO programDTO) {
        try {
            TrainingProgramDTO created = trainingProgramService.createTrainingProgram(programDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getTrainingProgram(@PathVariable Long id) {
        TrainingProgramDTO program = trainingProgramService.getTrainingProgramById(id);
        if (program != null) {
            return new ResponseEntity<>(program, HttpStatus.OK);
        }
        return new ResponseEntity<>("Training program not found", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping
    public ResponseEntity<?> getAllTrainingPrograms() {
        List<TrainingProgramDTO> programs = trainingProgramService.getAllTrainingPrograms();
        return new ResponseEntity<>(programs, HttpStatus.OK);
    }
    
    @GetMapping("/scheduled")
    public ResponseEntity<?> getScheduledTrainings() {
        List<TrainingProgramDTO> programs = trainingProgramService.getScheduledTrainings();
        return new ResponseEntity<>(programs, HttpStatus.OK);
    }
    
    @GetMapping("/ongoing")
    public ResponseEntity<?> getOngoingTrainings() {
        List<TrainingProgramDTO> programs = trainingProgramService.getOngoingTrainings();
        return new ResponseEntity<>(programs, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrainingProgram(@PathVariable Long id, @RequestBody TrainingProgramDTO programDTO) {
        try {
            TrainingProgramDTO updated = trainingProgramService.updateTrainingProgram(id, programDTO);
            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            }
            return new ResponseEntity<>("Training program not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainingProgram(@PathVariable Long id) {
        try {
            trainingProgramService.deleteTrainingProgram(id);
            return new ResponseEntity<>("Training program deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

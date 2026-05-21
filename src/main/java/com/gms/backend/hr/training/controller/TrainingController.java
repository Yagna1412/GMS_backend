package com.gms.backend.hr.training.controller;

import com.gms.backend.hr.training.dto.TrainingProgramDTO;
import com.gms.backend.hr.training.entity.TrainingFeedback;
import com.gms.backend.hr.training.entity.TrainingMaterial;
import com.gms.backend.hr.training.entity.TrainingProgram;
import com.gms.backend.hr.training.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    // 1. Fetch all training programs
    @GetMapping("/training-programs")
    public List<TrainingProgram> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    // 2. Fetch single training program details
    @GetMapping("/training-programs/{id}")
    public TrainingProgram getTrainingById(@PathVariable Long id) {
        return trainingService.getTrainingById(id);
    }

    // 3. Create a new training program tracking entry
    @PostMapping("/training-programs")
    public TrainingProgram createTraining(@RequestBody TrainingProgramDTO dto) {
        return trainingService.createTraining(dto);
    }

    // 4. Update core training program parameters
    @PutMapping("/training-programs/{id}")
    public TrainingProgram updateTraining(@PathVariable Long id, @RequestBody TrainingProgramDTO dto) {
        return trainingService.updateTraining(id, dto);
    }

    // 5. Delete training program resource
    @DeleteMapping("/training-programs/{id}")
    public void deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
    }

    // 6. Filter and retrieve programs by current status phase
    @GetMapping("/training-programs/status/{status}")
    public List<TrainingProgram> getProgramsByStatus(@PathVariable String status) {
        return trainingService.getProgramsByStatus(status);
    }

    // 7. Filter and retrieve programs by structural category type
    @GetMapping("/training-programs/type/{type}")
    public List<TrainingProgram> getProgramsByType(@PathVariable String type) {
        return trainingService.getProgramsByType(type);
    }

    // 13. Expose summary counters feeding dashboard UI views
    @GetMapping("/training-analytics")
    public ResponseEntity<Object> getTrainingAnalytics() {
        return ResponseEntity.ok(trainingService.getAnalyticsDashboardData());
    }

    // 14. Aggregate cost fields for capital investment reviews
    @GetMapping("/training-investments")
    public ResponseEntity<Object> getTrainingInvestments() {
        return ResponseEntity.ok(trainingService.getInvestmentReports());
    }

    // 15. Stream date sequences to feed operational timeline calendars
    @GetMapping("/training-calendar")
    public ResponseEntity<Object> getTrainingCalendar() {
        return ResponseEntity.ok(trainingService.getTrainingCalendarData());
    }

    // 16. Trigger background workers to dispatch messaging notifications
    @PostMapping("/training-notifications")
    public ResponseEntity<String> sendNotifications() {
        trainingService.sendTrainingNotifications();
        return ResponseEntity.ok("Training Notification Services Initiated Successfully.");
    }

    // Media Resources & Review Handling Pipelines
    @PostMapping("/training-feedback")
    public TrainingFeedback createFeedback(@RequestBody TrainingFeedback feedback) {
        return trainingService.createFeedback(feedback);
    }

    @GetMapping("/training-feedback")
    public List<TrainingFeedback> getAllFeedback() {
        return trainingService.getAllFeedback();
    }

    @PostMapping("/training-materials")
    public TrainingMaterial createMaterial(@RequestBody TrainingMaterial material) {
        return trainingService.createMaterial(material);
    }

    @GetMapping("/training-materials")
    public List<TrainingMaterial> getAllMaterials() {
        return trainingService.getAllMaterials();
    }
}
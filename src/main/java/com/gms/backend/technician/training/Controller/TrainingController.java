package com.gms.backend.technician.training.Controller;

import com.gms.backend.technician.training.Dto.TrainingDTO;
import com.gms.backend.technician.training.Entity.Material;
import com.gms.backend.technician.training.Repository.MaterialRepository;
import com.gms.backend.technician.training.Service.TrainingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/trainings")
public class TrainingController {

    @Autowired
    private TrainingService service;

    @Autowired
    private MaterialRepository materialRepo;

    // Get Trainings by User ID
    @GetMapping("/{userId}")
    public List<TrainingDTO> getTrainings(@PathVariable Long userId) {
        return service.getTrainings(userId);
    }

    // Get Materials by Training ID
    @GetMapping("/materials/{trainingId}")
    public List<Material> getMaterials(@PathVariable Long trainingId) {
        return materialRepo.findByTrainingId(trainingId);
    }

    // Update Training Progress
    @PutMapping("/{id}/progress")
    public void updateProgress(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> req
    ) {
        service.updateProgress(id, req.get("progress"));
    }
}
package com.gms.backend.hr.training.controller;

import com.gms.backend.hr.training.dto.TrainerDTO;
import com.gms.backend.hr.training.entity.Trainer;
import com.gms.backend.hr.training.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    // 11. Add a specialized instructor profile
    @PostMapping
    public Trainer createTrainer(@RequestBody TrainerDTO dto) {
        return trainerService.createTrainer(dto);
    }

    // 12. Update trainer records, fields, and metrics (FIXED: Added missing endpoint)
    @PutMapping("/{id}")
    public Trainer updateTrainer(@PathVariable Long id, @RequestBody TrainerDTO dto) {
        return trainerService.updateTrainer(id, dto);
    }

    // 10. Fetch all registered trainers
    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @GetMapping("/{id}")
    public Trainer getTrainerById(@PathVariable Long id) {
        return trainerService.getTrainerById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
    }
}
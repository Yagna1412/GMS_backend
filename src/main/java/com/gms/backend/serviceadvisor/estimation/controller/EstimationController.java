package com.gms.backend.serviceadvisor.estimation.controller;

import com.gms.backend.serviceadvisor.estimation.dto.*;
import com.gms.backend.serviceadvisor.estimation.service.EstimationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/estimations")
@RequiredArgsConstructor
public class EstimationController {

    private final EstimationService estimationService;

    @GetMapping("/summary")
    public ResponseEntity<EstimationSummaryDTO> getSummary() {
        return ResponseEntity.ok(estimationService.getSummary());
    }

    @GetMapping
    public ResponseEntity<List<EstimationDTO>> getAllEstimations() {
        return ResponseEntity.ok(estimationService.getAllEstimations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstimationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(estimationService.getEstimationById(id));
    }

    @PostMapping
    public ResponseEntity<EstimationDTO> create(@Valid @RequestBody EstimationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estimationService.createEstimation(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstimationDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody EstimationDTO dto) {
        return ResponseEntity.ok(estimationService.updateEstimation(id, dto));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EstimationDTO> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody StatusUpdateDTO dto) {
        return ResponseEntity.ok(estimationService.updateStatus(id, dto.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estimationService.deleteEstimation(id);
        return ResponseEntity.noContent().build();
    }
}
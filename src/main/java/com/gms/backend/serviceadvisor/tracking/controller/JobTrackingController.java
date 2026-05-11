package com.gms.backend.serviceadvisor.tracking.controller;

import com.gms.backend.serviceadvisor.tracking.dto.JobTrackingDto;
import com.gms.backend.serviceadvisor.tracking.entity.JobTracking;
import com.gms.backend.serviceadvisor.tracking.service.JobTrackingService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/tracking")
public class JobTrackingController {

    private final JobTrackingService service;

    public JobTrackingController(JobTrackingService service) {
        this.service = service;
    }

    // =========================
    // CREATE
    // =========================
    @PostMapping
    public JobTracking createTracking(
            @RequestBody JobTrackingDto dto
    ) {
        return service.createTracking(dto);
    }

    // =========================
    // GET ALL JOBS
    // =========================
    @GetMapping("/jobs")
    public List<JobTrackingDto> getAllJobs() {
        return service.getAllJobStatuses();
    }

    // =========================
    // SUMMARY
    // =========================
    @GetMapping("/summary")
    public Map<String, Long> getSummary() {
        return service.getSummaryCounts();
    }

    // =========================
    // UPDATE
    // =========================
    @PutMapping("/{jobCardId}")
    public JobTracking updateTracking(
            @PathVariable Long jobCardId,
            @RequestBody JobTrackingDto dto
    ) {
        return service.updateTracking(jobCardId, dto);
    }

    // =========================
    // DELETE
    // =========================
    @DeleteMapping("/{jobCardId}")
    public String deleteTracking(
            @PathVariable Long jobCardId
    ) {

        service.deleteByJobCardId(jobCardId);

        return "Job deleted successfully";
    }
}
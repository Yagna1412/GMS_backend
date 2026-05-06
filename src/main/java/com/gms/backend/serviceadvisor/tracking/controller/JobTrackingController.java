package com.gms.backend.serviceadvisor.tracking.controller;

import com.gms.backend.serviceadvisor.tracking.dto.JobTrackingDto;
import com.gms.backend.serviceadvisor.tracking.entity.JobTracking;
import com.gms.backend.serviceadvisor.tracking.service.JobTrackingService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tracking")
public class JobTrackingController {

    private final JobTrackingService service;

    public JobTrackingController(JobTrackingService service) {
        this.service = service;
    }

    /*// CREATE
    @PostMapping
    public JobTracking createTracking(@RequestBody JobTrackingDto dto) {
        return service.createTracking(dto);
    }

    // GET HISTORY (single job)
    @GetMapping("/{jobCardId}")
    public List<JobTracking> getTracking(@PathVariable Long jobCardId) {
        return service.getTrackingByJob(jobCardId);
    }*/

    // JOB TABLE
    @GetMapping("/jobs")
    public List<JobTrackingDto> getAllJobs() {
        return service.getAllJobStatuses();
    }

    // SUMMARY CARDS
    @GetMapping("/summary")
    public Map<String, Long> getSummary() {
        return service.getSummaryCounts();
    }

    // UPDATE (FIXED)
    @PutMapping("/{jobCardId}")
    public JobTracking updateTracking(@PathVariable Long jobCardId,
                                      @RequestBody JobTrackingDto dto) {
        return service.updateTracking(jobCardId, dto);
    }

    // DELETE (NEW)
    @DeleteMapping("/{jobCardId}")
    public void deleteTracking(@PathVariable Long jobCardId) {
        service.deleteTracking(jobCardId);
    }
}
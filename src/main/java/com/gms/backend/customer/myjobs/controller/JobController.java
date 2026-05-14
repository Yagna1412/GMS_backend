package com.gms.backend.customer.myjobs.controller;

import com.gms.backend.customer.myjobs.dto.JobDetailsDTO;
import com.gms.backend.customer.myjobs.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class JobController {

    private final JobService jobService;

    // ================== GET ALL JOBS ==================
    // GET /api/jobs
    @GetMapping
    public List<JobDetailsDTO> getJobs() {
        return jobService.getAllJobs();
    }

    // ================== GET JOBS BY STATUS ==================
    // GET /api/jobs/status?status=Pending
    @GetMapping("/status")
    public List<JobDetailsDTO> getJobsByStatus(
            @RequestParam String status) {
        return jobService.getJobsByStatus(status);
    }

    // ================== GET JOB DETAILS BY ID ==================
    // GET /api/jobs/1
    @GetMapping("/{id}")
    public JobDetailsDTO getJobDetails(
            @PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // ================== UPDATE JOB STATUS ==================
    // PUT /api/jobs/1/status?status=Completed
    @PutMapping("/{id}/status")
    public JobDetailsDTO updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return jobService.updateJobStatus(id, status);
    }

    // ================== DELETE JOB ==================
    // DELETE /api/jobs/1
    @DeleteMapping("/{id}")
    public String deleteJob(
            @PathVariable Long id) {
        jobService.deleteJob(id);
        return "Job deleted successfully with id: " + id;
    }
}
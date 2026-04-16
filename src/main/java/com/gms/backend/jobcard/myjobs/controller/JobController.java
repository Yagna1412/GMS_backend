package com.gms.backend.jobcard.myjobs.controller;

import com.gms.backend.jobcard.myjobs.dto.JobDetailsDTO;
import com.gms.backend.jobcard.myjobs.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class JobController {

    private final JobService jobService;

    // 1. Get All Jobs
    @GetMapping
    public List<JobDetailsDTO> getJobs() {
        return jobService.getAllJobs();
    }

    // 2. Get Jobs By Status
    @GetMapping("/status")
    public List<JobDetailsDTO> getJobsByStatus(@RequestParam String status) {
        return jobService.getJobsByStatus(status);
    }

    // 3. Get Job Details by ID
    @GetMapping("/{id}")
    public JobDetailsDTO getJobDetails(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // 4. Update Job Status
    @PutMapping("/{id}/status")
    public JobDetailsDTO updateStatus(@PathVariable Long id, @RequestParam String status) {
        return jobService.updateJobStatus(id, status);
    }

    // 5. Delete Job
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "Job deleted successfully with id: " + id;
    }
}
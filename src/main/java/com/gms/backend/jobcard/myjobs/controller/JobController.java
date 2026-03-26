package com.gms.backend.jobcard.myjobs.controller;

import com.gms.backend.jobcard.myjobs.dto.JobDetailsDTO;
import com.gms.backend.jobcard.myjobs.dto.JobsDTO;
import com.gms.backend.jobcard.myjobs.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class JobController {

    private final JobService jobService;

    // 1 + 2
    @GetMapping
    public List<JobDetailsDTO> getJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/status")
    public List<JobsDTO> getJobsByStatus(
            @RequestParam(required = false) String status) {

        if (status != null) {
            return jobService.getJobsByStatus(status);
        }

        // optional: return all jobs if no status passed
        return jobService.getJobsByStatus("BOOKED");
    }

    @GetMapping("/{id}")
    public JobDetailsDTO getJobDetails(@PathVariable Long id) {
        return jobService.getJobById(id);
    }


    @PutMapping("/{id}/status")
    public JobDetailsDTO updateStatus(@PathVariable Long id, @RequestParam String status) {
        return jobService.updateJobStatus(id, status);
    }
    // ✅ DELETE Job by ID
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "Job deleted successfully with id: " + id;
    }
}
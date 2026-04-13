package com.gms.backend.jobcard.controller;

import com.gms.backend.jobcard.JobCard;
import com.gms.backend.jobcard.Repository.JobCardRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs") // Changed base path to avoid conflict
public class JobCardController {

    private final JobCardRepository repo;

    public JobCardController(JobCardRepository repo) {
        this.repo = repo;
    }

    // Changed path to /api/jobs/create to avoid conflict with /api/service/book
    @PostMapping("/create")
    public JobCard createJobCard(@RequestBody JobCard jobCard) {
        return repo.save(jobCard);
    }

    @GetMapping("/status")
    public List<JobCard> getJobStatus() {
        // Implementation for API #9: Track Job
        return repo.findAll();
    }
}
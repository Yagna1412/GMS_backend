package com.gms.backend.technician.partsrequest.controller;

import com.gms.backend.technician.partsrequest.repository.PartsRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/job-cards")
public class JobCardController {
    private final PartsRequestRepo partsRequestRepo;

    @Autowired
    public JobCardController(PartsRequestRepo partsRequestRepo) {
        this.partsRequestRepo = partsRequestRepo;
    }

    // Endpoint to get all active job card IDs for dropdown (for the modal in the image)
    @GetMapping("/active")
    public List<String> getActiveJobCardIds() {
        return partsRequestRepo.findDistinctActiveJobCardIds();
    }
}


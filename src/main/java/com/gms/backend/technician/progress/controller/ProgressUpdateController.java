package com.gms.backend.technician.progress.controller;

import com.gms.backend.technician.progress.service.ProgressUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressUpdateController {

    @Autowired
    private ProgressUpdateService service;

    //  ADD UPDATE
    @PostMapping("/add")
    public ResponseEntity<?> addUpdate(
            @RequestParam String jobId,
            @RequestParam String note,
            @RequestParam(required = false) List<MultipartFile> files
    ) {
        return ResponseEntity.ok(service.addUpdate(jobId, note, files));
    }

    //  GET UPDATES
    @GetMapping("/{jobId}")
    public ResponseEntity<?> getUpdates(@PathVariable String jobId) {
        return ResponseEntity.ok(service.getUpdates(jobId));
    }

    //  ACTIVE JOBS API
    @GetMapping("/active-jobs")
    public ResponseEntity<?> getActiveJobs() {
        return ResponseEntity.ok(service.getActiveJobs());
    }
}
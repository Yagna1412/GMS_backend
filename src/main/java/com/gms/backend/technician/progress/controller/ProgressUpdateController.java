package com.gms.backend.technician.progress.controller;

import com.gms.backend.technician.progress.service.ProgressUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS
        }
)
@RestController
@RequestMapping("/api/progress")
public class ProgressUpdateController {

    @Autowired
    private ProgressUpdateService service;

    // ADD UPDATE
    @PostMapping("/add")
    public ResponseEntity<?> addUpdate(

            @RequestParam("jobId") String jobId,

            @RequestParam("note") String note,

            @RequestParam(value = "files", required = false)
            List<MultipartFile> files
    ) {

        try {

            return ResponseEntity.ok(
                    service.addUpdate(jobId, note, files)
            );

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // GET ALL UPDATES BY JOB ID
    @GetMapping("/{jobId}")
    public ResponseEntity<?> getUpdates(
            @PathVariable String jobId
    ) {

        try {

            return ResponseEntity.ok(
                    service.getUpdates(jobId)
            );

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // GET ACTIVE JOBS
    @GetMapping("/active-jobs")
    public ResponseEntity<?> getActiveJobs() {

        try {

            return ResponseEntity.ok(
                    service.getActiveJobs()
            );

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}
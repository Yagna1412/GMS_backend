package com.gms.backend.technician.qc.controller;

import com.gms.backend.technician.qc.dto.*;
import com.gms.backend.technician.qc.service.QCService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qc")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class QCController {


     private final QCService service;

        @GetMapping("/dashboard")
        public DashboardResponseDTO dashboard() {
            return service.getDashboard();
        }

        @GetMapping("/jobs")
        public List<JobCardDTO> readyJobs() {
            return service.getReadyJobs();
        }

        @PostMapping("/checklist")
        public String saveChecklist(@RequestBody QCChecklistRequestDTO request) {
            service.saveChecklist(request);
            return "Checklist saved";
        }

        @GetMapping("/checklist/{jobId}")
        public List<QCChecklistDTO> getChecklist(@PathVariable Long jobId) {
            return service.getChecklist(jobId);
        }

        @PostMapping("/submit")
        public String submit(@RequestBody QCSubmitRequestDTO request) {
            service.submitQC(request);
            return "Submitted for QC";
        }
    }


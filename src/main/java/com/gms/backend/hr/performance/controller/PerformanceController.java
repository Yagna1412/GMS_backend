package com.gms.backend.hr.performance.controller;

import com.gms.backend.hr.performance.dto.*;
import com.gms.backend.hr.performance.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr/performance")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PerformanceController {

    private final PerformanceService service;

    // Dashboard API
    @GetMapping("/dashboard")
    public DashboardStatsDto
    getDashboardStats() {

        return service.getDashboardStats();
    }

    // Get All Reviews
    @GetMapping("/reviews")
    public List<PerformanceReviewResponseDto>
    getAllReviews() {

        return service.getAllReviews();
    }

    // Get Review By Id
    @GetMapping("/reviews/{id}")
    public PerformanceReviewResponseDto
    getReviewById(
            @PathVariable Long id) {

        return service.getReviewById(id);
    }

    // Create Review
    @PostMapping("/reviews")
    public PerformanceReviewResponseDto
    createReview(
            @RequestBody
            PerformanceReviewRequestDto request) {

        return service.createReview(request);
    }

    // Update Review
    @PutMapping("/reviews/{id}")
    public PerformanceReviewResponseDto
    updateReview(
            @PathVariable Long id,
            @RequestBody
            PerformanceReviewRequestDto request) {

        return service.updateReview(id, request);
    }

    // Delete Review
    @DeleteMapping("/reviews/{id}")
    public String deleteReview(
            @PathVariable Long id) {

        return service.deleteReview(id);
    }

    // Create Appraisal Cycle
    @PostMapping("/cycles")
    public AppraisalCycleDto
    createCycle(
            @RequestBody
            AppraisalCycleDto request) {

        return service.createCycle(request);
    }

    // Get All Cycles
    @GetMapping("/cycles")
    public List<AppraisalCycleDto>
    getAllCycles() {

        return service.getAllCycles();
    }
}
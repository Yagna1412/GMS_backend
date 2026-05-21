package com.gms.backend.hr.performance.service;

import com.gms.backend.hr.performance.dto.*;
import com.gms.backend.hr.performance.entity.*;
import com.gms.backend.hr.performance.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerformanceService {

    private final PerformanceRepository repository;

    private final AppraisalCycleRepository
            appraisalCycleRepository;

    // Dashboard Statistics
    public DashboardStatsDto getDashboardStats() {

        long pendingReviews =
                repository.countByStatus("PENDING");

        long completedReviews =
                repository.countByStatus("COMPLETED");

        List<PerformanceReview> reviews =
                repository.findAll();

        double averageScore =
                reviews.stream()
                        .mapToDouble(
                                PerformanceReview::getOverallScore)
                        .average()
                        .orElse(0.0);

        return DashboardStatsDto.builder()
                .pendingReviews(pendingReviews)
                .completedReviews(completedReviews)
                .averageScore(averageScore)
                .build();
    }

    // Get All Reviews
    public List<PerformanceReviewResponseDto>
    getAllReviews() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get Review By Id
    public PerformanceReviewResponseDto
    getReviewById(Long id) {

        PerformanceReview review =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Review not found"));

        return mapToResponse(review);
    }

    // Create Review
    public PerformanceReviewResponseDto
    createReview(
            PerformanceReviewRequestDto request) {

        PerformanceReview review =
                PerformanceReview.builder()
                        .employeeId(request.getEmployeeId())
                        .cycleId(request.getCycleId())
                        .reviewerId(request.getReviewerId())
                        .employeeName(request.getEmployeeName())
                        .employeeCode(request.getEmployeeCode())
                        .cycle(request.getCycle())
                        .reviewer(request.getReviewer())
                        .reviewDate(request.getReviewDate())
                        .overallScore(request.getOverallScore())
                        .status(request.getStatus())
                        .comments(request.getComments())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();

        return mapToResponse(
                repository.save(review));
    }

    // Update Review
    public PerformanceReviewResponseDto
    updateReview(
            Long id,
            PerformanceReviewRequestDto request) {

        PerformanceReview review =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Review not found"));

        review.setEmployeeId(
                request.getEmployeeId());

        review.setCycleId(
                request.getCycleId());

        review.setReviewerId(
                request.getReviewerId());

        review.setEmployeeName(
                request.getEmployeeName());

        review.setEmployeeCode(
                request.getEmployeeCode());

        review.setCycle(
                request.getCycle());

        review.setReviewer(
                request.getReviewer());

        review.setReviewDate(
                request.getReviewDate());

        review.setOverallScore(
                request.getOverallScore());

        review.setStatus(
                request.getStatus());

        review.setComments(
                request.getComments());

        review.setUpdatedAt(
                LocalDateTime.now());

        return mapToResponse(
                repository.save(review));
    }

    // Delete Review
    public String deleteReview(Long id) {

        PerformanceReview review =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Review not found"));

        repository.delete(review);

        return "Review deleted successfully";
    }

    // Create Appraisal Cycle
    public AppraisalCycleDto
    createCycle(
            AppraisalCycleDto request) {

        AppraisalCycle cycle =
                AppraisalCycle.builder()
                        .cycleName(request.getCycleName())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .status(request.getStatus())
                        .createdAt(LocalDateTime.now())
                        .build();

        AppraisalCycle saved =
                appraisalCycleRepository.save(cycle);

        return mapCycleResponse(saved);
    }

    // Get All Cycles
    public List<AppraisalCycleDto>
    getAllCycles() {

        return appraisalCycleRepository.findAll()
                .stream()
                .map(this::mapCycleResponse)
                .collect(Collectors.toList());
    }

    // Review DTO Mapper
    private PerformanceReviewResponseDto
    mapToResponse(
            PerformanceReview review) {

        return PerformanceReviewResponseDto.builder()
                .reviewId(review.getReviewId())
                .employeeName(review.getEmployeeName())
                .employeeCode(review.getEmployeeCode())
                .cycle(review.getCycle())
                .reviewer(review.getReviewer())
                .reviewDate(review.getReviewDate())
                .overallScore(review.getOverallScore())
                .status(review.getStatus())
                .comments(review.getComments())
                .build();
    }

    // Cycle DTO Mapper
    private AppraisalCycleDto
    mapCycleResponse(
            AppraisalCycle cycle) {

        return AppraisalCycleDto.builder()
                .cycleId(cycle.getCycleId())
                .cycleName(cycle.getCycleName())
                .startDate(cycle.getStartDate())
                .endDate(cycle.getEndDate())
                .status(cycle.getStatus())
                .build();
    }
}
package com.gms.backend.hr.dashboard.controller;

import com.gms.backend.hr.dashboard.dto.PerformanceReviewDTO;
import com.gms.backend.hr.dashboard.service.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hr/performance-reviews")
@CrossOrigin("*")
public class PerformanceReviewController {
    
    @Autowired
    private PerformanceReviewService performanceReviewService;
    
    @PostMapping
    public ResponseEntity<?> createPerformanceReview(@RequestBody PerformanceReviewDTO reviewDTO) {
        try {
            PerformanceReviewDTO created = performanceReviewService.createPerformanceReview(reviewDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getPerformanceReview(@PathVariable Long id) {
        PerformanceReviewDTO review = performanceReviewService.getPerformanceReviewById(id);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>("Performance review not found", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEmployeePerformanceReviews(@PathVariable Long employeeId) {
        List<PerformanceReviewDTO> reviews = performanceReviewService.getEmployeePerformanceReviews(employeeId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    
    @GetMapping("/reviewer/{reviewerId}/pending")
    public ResponseEntity<?> getReviewerPendingReviews(@PathVariable Long reviewerId) {
        List<PerformanceReviewDTO> reviews = performanceReviewService.getReviewerPendingReviews(reviewerId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/submit")
    public ResponseEntity<?> submitPerformanceReview(@PathVariable Long id, @RequestBody PerformanceReviewDTO reviewDTO) {
        try {
            PerformanceReviewDTO submitted = performanceReviewService.submitPerformanceReview(id, reviewDTO);
            if (submitted != null) {
                return new ResponseEntity<>(submitted, HttpStatus.OK);
            }
            return new ResponseEntity<>("Performance review not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerformanceReview(@PathVariable Long id) {
        try {
            performanceReviewService.deletePerformanceReview(id);
            return new ResponseEntity<>("Performance review deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/count/pending")
    public ResponseEntity<?> getPendingReviewCount() {
        long count = performanceReviewService.getPendingReviewCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}

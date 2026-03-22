package com.gms.backend.customer.feedback.controller;

import com.gms.backend.customer.feedback.dto.CreateReviewRequest;
import com.gms.backend.customer.feedback.dto.ReviewResponseDTO;
import com.gms.backend.customer.feedback.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // ─────────────────────────────────────────────
    // GET /api/reviews?customerId=1
    // My Reviews tab — fetch all reviews
    // ─────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> getMyReviews(
            @RequestParam Long customerId
    ) {
        return ResponseEntity.ok(reviewService.getReviewsByCustomer(customerId));
    }

    // ─────────────────────────────────────────────
    // POST /api/reviews
    // Rate Service modal — submit a new review
    // ─────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<ReviewResponseDTO> submitReview(
            @RequestBody CreateReviewRequest request
    ) {
        ReviewResponseDTO created = reviewService.createReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
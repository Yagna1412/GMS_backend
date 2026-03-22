package com.gms.backend.customer.feedback.service;

import com.gms.backend.customer.feedback.dto.CreateReviewRequest;
import com.gms.backend.customer.feedback.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {

    // GET /api/reviews?customerId=1
    // Returns all reviews for the My Reviews tab
    List<ReviewResponseDTO> getReviewsByCustomer(Long customerId);

    // POST /api/reviews
    // Submits a new review from Rate Service modal
    ReviewResponseDTO createReview(CreateReviewRequest request);
}
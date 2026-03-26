package com.gms.backend.customer.feedback.service;

import com.gms.backend.customer.feedback.dto.CreateReviewRequest;
import com.gms.backend.customer.feedback.dto.ReviewResponseDTO;
import com.gms.backend.customer.feedback.entity.Review;
import com.gms.backend.customer.feedback.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    // ─────────────────────────────────────────────
    // GET /api/reviews?customerId=1
    // ─────────────────────────────────────────────
    @Override
    public List<ReviewResponseDTO> getReviewsByCustomer(Long customerId) {
        List<Review> reviews = reviewRepository
                .findByCustomerIdOrderByFeedbackDateDesc(customerId);

        AtomicInteger counter = new AtomicInteger(1);
        return reviews.stream()
                .map(review -> mapToDTO(review, counter.getAndIncrement()))
                .collect(Collectors.toList());
    }

    // ─────────────────────────────────────────────
    // POST /api/reviews
    // Rate Service modal — saves and returns the new review
    // ─────────────────────────────────────────────
    @Override
    public ReviewResponseDTO createReview(CreateReviewRequest request) {
        Review review = Review.builder()
                .customerId(request.getCustomerId())
                .serviceName(request.getServiceType())  // service type from dropdown
                .overallRating(request.getOverallRating())
                .comments(request.getComments())
                .feedbackDate(LocalDate.now())
                .build();

        Review saved = reviewRepository.save(review);

        // Count total reviews for this customer to set reviewNumber
        int totalReviews = reviewRepository
                .findByCustomerIdOrderByFeedbackDateDesc(request.getCustomerId())
                .size();

        return mapToDTO(saved, totalReviews);
    }

    // ─────────────────────────────────────────────
    // Private mapper
    // ─────────────────────────────────────────────
    private ReviewResponseDTO mapToDTO(Review review, int reviewNumber) {
        return ReviewResponseDTO.builder()
                .reviewNumber((long) reviewNumber)
                .id(review.getId())
                .customerId(review.getCustomerId())
                .jobCardId(review.getJobCardId())
                .serviceName(review.getServiceName())
                .branchName(review.getBranchName())
                .overallRating(review.getOverallRating())
                .technicianRating(review.getTechnicianRating())
                .advisorRating(review.getAdvisorRating())
                .facilityRating(review.getFacilityRating())
                .comments(review.getComments())
                .feedbackDate(review.getFeedbackDate())
                .build();
    }
    @Override
    public ReviewResponseDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        return mapToDTO(review, review.getId().intValue());
    }
}
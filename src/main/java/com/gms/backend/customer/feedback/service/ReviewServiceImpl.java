package com.gms.backend.customer.feedback.service;

import com.gms.backend.customer.feedback.dto.ReviewResponseDTO;
import com.gms.backend.customer.feedback.entity.Review;
import com.gms.backend.customer.feedback.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewResponseDTO> getReviewsByCustomer(Long customerId) {

        List<Review> reviews = reviewRepository
                .findByCustomerIdOrderByFeedbackDateDesc(customerId);

        // reviewNumber is a sequential badge shown in the UI (1, 2, 3...)
        AtomicInteger counter = new AtomicInteger(1);

        return reviews.stream()
                .map(review -> mapToDTO(review, counter.getAndIncrement()))
                .collect(Collectors.toList());
    }

    // -------------------------------------------------------
    // Private mapper — converts entity to DTO
    // -------------------------------------------------------
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
}
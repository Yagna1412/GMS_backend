package com.gms.backend.customer.feedback.service;

import com.gms.backend.customer.feedback.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {

    /**
     * Returns all reviews submitted by the given customer.
     * Results are ordered newest first.
     *
     * @param customerId the ID of the logged-in customer
     * @return list of ReviewResponseDTO
     */
    List<ReviewResponseDTO> getReviewsByCustomer(Long customerId);
}

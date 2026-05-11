package com.gms.backend.customer.feedbacks.repository;

import com.gms.backend.customer.feedbacks.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Fetch all reviews for a specific customer, newest first
    List<Review> findByCustomerIdOrderByFeedbackDateDesc(Long customerId);
}
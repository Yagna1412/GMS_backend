package com.gms.backend.customer.feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest {

    private Long customerId;      // who is submitting the review
    private String serviceType;   // selected from dropdown e.g. "General Service"
    private Integer overallRating; // 1 to 5 stars
    private String comments;      // written feedback text
}
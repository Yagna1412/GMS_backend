package com.gms.backend.customer.feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {

    // Review number shown as badge in UI (e.g. "4", "5")
    private Long reviewNumber;

    private Long id;

    private Long customerId;

    private Long jobCardId;

    // Shown as title in UI: "General Service - Downtown Center"
    private String serviceName;
    private String branchName;

    // Ratings
    private Integer overallRating;
    private Integer technicianRating;
    private Integer advisorRating;
    private Integer facilityRating;

    // Written comment shown in card
    private String comments;

    // Date shown in card (e.g. "12 Oct 2025")
    private LocalDate feedbackDate;
}

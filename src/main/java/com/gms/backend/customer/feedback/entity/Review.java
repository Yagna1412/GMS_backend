package com.gms.backend.customer.feedback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The customer who submitted this review
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    // Linked job card
    @Column(name = "job_card_id")
    private Long jobCardId;

    // Service name stored directly (e.g. "General Service")
    @Column(name = "service_name")
    private String serviceName;

    // Branch name stored directly (e.g. "Downtown Center")
    @Column(name = "branch_name")
    private String branchName;

    // Ratings (1 to 5)
    @Column(name = "overall_rating", nullable = false)
    private Integer overallRating;

    @Column(name = "technician_rating")
    private Integer technicianRating;

    @Column(name = "advisor_rating")
    private Integer advisorRating;

    @Column(name = "facility_rating")
    private Integer facilityRating;

    // Written feedback
    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    // Photo URLs stored as comma-separated string
    @Column(name = "photo_urls", columnDefinition = "TEXT")
    private String photoUrls;

    // Date the feedback was given
    @Column(name = "feedback_date")
    private LocalDate feedbackDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.feedbackDate == null) {
            this.feedbackDate = LocalDate.now();
        }
    }
}
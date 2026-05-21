package com.gms.backend.hr.performance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "performance_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Long employeeId;

    private Long cycleId;

    private Long reviewerId;

    private String employeeName;

    private String employeeCode;

    private String cycle;

    private String reviewer;

    private LocalDate reviewDate;

    private Double overallScore;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String comments;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
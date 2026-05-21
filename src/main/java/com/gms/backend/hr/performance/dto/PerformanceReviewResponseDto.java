package com.gms.backend.hr.performance.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReviewResponseDto {

    private Long reviewId;

    private String employeeName;

    private String employeeCode;

    private String cycle;

    private String reviewer;

    private LocalDate reviewDate;

    private Double overallScore;

    private String status;

    private String comments;
}
package com.gms.backend.hr.performance.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDto {

    private long pendingReviews;

    private long completedReviews;

    private double averageScore;
}
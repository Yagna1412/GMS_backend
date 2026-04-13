package com.gms.backend.dashboard.customerdashboarddto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryDTO {
    private int upcoming;
    private int activeJobs;
    private int loyaltyPoints;
    private double totalSpent;
}
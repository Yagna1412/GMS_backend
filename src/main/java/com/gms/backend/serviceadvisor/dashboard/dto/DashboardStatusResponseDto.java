package com.gms.backend.serviceadvisor.dashboard.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatusResponseDto {
    private long totalJobCards;
    private BigDecimal estimationValue;
    private int completionRate;
}
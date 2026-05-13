package com.gms.backend.serviceadvisor.dashboard.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingEstimationDto {
    private Long estimationId;
    private String estimationNumber;
    private String customerName;
    private BigDecimal amount;
    private String status;
}
package com.gms.backend.serviceadvisor.estimation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstimationSummaryDTO {
    private long pending;          // Pending card
    private long approved;         // Approved card
    private BigDecimal totalValue; // Total Value card (₹9K)
}

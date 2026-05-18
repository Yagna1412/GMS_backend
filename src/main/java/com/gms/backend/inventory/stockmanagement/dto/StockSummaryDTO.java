package com.gms.backend.inventory.stockmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockSummaryDTO {
    private long totalMovements;
    private long inward;
    private long outward;
    private long adjustments;
}
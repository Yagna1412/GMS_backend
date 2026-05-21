package com.gms.backend.inventory.reports.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryValuationSummaryDto {

    private BigDecimal totalInventoryValue;
    private BigDecimal potentialRevenue;
    private BigDecimal potentialProfit;
    private String currency;
}

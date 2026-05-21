package com.gms.backend.inventory.dashboard.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardSummaryDTO {

    private BigDecimal totalInventoryValue;

    private Double inventoryGrowth;

    private Long activeItems;

    private Integer activeItemGrowth;

    private Long lowStockAlerts;

    private Long outOfStockItems;

    private String currency;
}

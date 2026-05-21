package com.gms.backend.inventory.dashboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAdjustmentDTO {

    private Long itemId;

    private String adjustmentType;

    private Integer quantity;

    private String remarks;
}

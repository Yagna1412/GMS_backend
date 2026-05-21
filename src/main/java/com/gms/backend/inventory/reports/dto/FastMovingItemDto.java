package com.gms.backend.inventory.reports.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FastMovingItemDto {

    private String itemName;
    private String sku;
    private String category;
    private Integer currentStock;
    private String turnoverRate;
}

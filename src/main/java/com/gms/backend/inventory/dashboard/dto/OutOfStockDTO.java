package com.gms.backend.inventory.dashboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutOfStockDTO {

    private Long itemId;

    private String itemName;

    private String skuCode;

    private Integer currentStock;

    private Integer reorderLevel;

    private String priority;
}

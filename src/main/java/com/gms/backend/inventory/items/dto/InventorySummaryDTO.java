package com.gms.backend.inventory.items.dto;

import lombok.Data;

@Data
public class InventorySummaryDTO {

    private long totalItems;
    private long activeItems;
    private long lowStock;
    private long outOfStock;
}
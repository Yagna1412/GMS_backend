package com.gms.backend.inventory.items.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventoryRequestDTO {

    private String sku;
    private String itemName;
    private String category;
    private BigDecimal costPrice;
    private BigDecimal sellingPrice;
    private Integer stockQuantity;
    private Integer minStock;
    private Integer maxStock;
    private String imageUrl;
}
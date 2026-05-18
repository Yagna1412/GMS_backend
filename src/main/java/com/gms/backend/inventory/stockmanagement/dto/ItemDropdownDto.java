package com.gms.backend.inventory.stockmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDropdownDto {
    private Long   id;
    private String sku;
    private String name;
}
package com.gms.backend.inventory.reports.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopHighValueItemDto {

    private Integer rank;
    private String itemName;
    private String sku;
    private Integer stock;
    private BigDecimal unitCost;
    private BigDecimal totalValue;
    private String imageUrl;
}

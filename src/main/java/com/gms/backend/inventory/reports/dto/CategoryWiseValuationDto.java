package com.gms.backend.inventory.reports.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryWiseValuationDto {

    private String category;
    private Long items;
    private BigDecimal value;
    private Double percentage;
    private String color;
}

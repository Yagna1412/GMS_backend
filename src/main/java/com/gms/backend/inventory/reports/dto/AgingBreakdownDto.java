package com.gms.backend.inventory.reports.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgingBreakdownDto {

    private String range;
    private Long count;
    private Double percentage;
}

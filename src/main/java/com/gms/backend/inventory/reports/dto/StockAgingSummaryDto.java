package com.gms.backend.inventory.reports.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAgingSummaryDto {

    private Long days0to30;
    private Long days30to60;
    private Long days60to180;
    private Long daysAbove180;
}

package com.gms.backend.inventory.dashboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockStatusOverviewDTO {

    private Long optimal;

    private Long lowStock;

    private Long critical;

    private Long overstock;
}

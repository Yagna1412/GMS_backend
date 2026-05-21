package com.gms.backend.inventory.vendors.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DashboardResponseDto {

    private Long totalVendors;

    private Long platinum;

    private Long gold;

    private Long silver;
}

package com.gms.backend.customer.reports.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySpendingDTO {

    private String month;
    private Double total;
}
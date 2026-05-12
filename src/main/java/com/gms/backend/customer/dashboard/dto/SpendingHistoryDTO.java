package com.gms.backend.customer.dashboard.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpendingHistoryDTO {
    private String month;
    private int amount;
}

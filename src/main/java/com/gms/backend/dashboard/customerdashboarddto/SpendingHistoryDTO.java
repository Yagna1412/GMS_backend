package com.gms.backend.dashboard.customerdashboarddto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpendingHistoryDTO {
    private String month;
    private int amount;
}
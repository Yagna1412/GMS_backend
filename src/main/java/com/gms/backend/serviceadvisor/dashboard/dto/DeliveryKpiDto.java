package com.gms.backend.serviceadvisor.dashboard.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryKpiDto {
    private long pending;
    private String status;
}

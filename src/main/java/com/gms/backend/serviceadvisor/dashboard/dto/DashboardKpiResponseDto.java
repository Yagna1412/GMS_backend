package com.gms.backend.serviceadvisor.dashboard.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardKpiResponseDto {
    private AppointmentKpiDto appointments;
    private EstimationKpiDto estimations;
    private JobKpiDto jobs;
    private DeliveryKpiDto deliveries;
}

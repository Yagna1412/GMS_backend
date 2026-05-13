package com.gms.backend.serviceadvisor.dashboard.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentScheduleDto {
    private Long appointmentId;
    private String time;
    private String customerName;
    private String vehicle;
    private String status;
}
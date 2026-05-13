package com.gms.backend.serviceadvisor.dashboard.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodayScheduleResponseDto {
    private LocalDate date;
    private List<AppointmentScheduleDto> appointments;
}
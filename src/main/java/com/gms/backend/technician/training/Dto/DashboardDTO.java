package com.gms.backend.technician.training.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardDTO {
    private long activeTrainings;
    private long completedTrainings;
    private long certifications;
    private long expiringSoon;
}
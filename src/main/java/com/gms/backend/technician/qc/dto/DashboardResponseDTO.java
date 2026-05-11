package com.gms.backend.technician.qc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDTO {

    private long readyForQc;
    private long qcPending;
    private double firstPassRate;

}

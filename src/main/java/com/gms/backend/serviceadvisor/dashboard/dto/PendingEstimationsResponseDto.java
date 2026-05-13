package com.gms.backend.serviceadvisor.dashboard.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingEstimationsResponseDto {
    private long count;
    private List<PendingEstimationDto> data;
}
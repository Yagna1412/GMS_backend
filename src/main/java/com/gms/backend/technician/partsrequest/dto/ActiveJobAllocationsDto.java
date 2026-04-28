package com.gms.backend.technician.partsrequest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ActiveJobAllocationsDto {
    private String jobCardId;
    private List<AllocatedPartResponseDto> parts;
}
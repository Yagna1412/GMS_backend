package com.gms.backend.technician.partsrequest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StatusUpdateDto {

    @NotBlank(message = "Status is required")
    private String status; // PENDING | APPROVED | REJECTED | RECEIVED
}
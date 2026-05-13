package com.gms.backend.serviceadvisor.estimation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StatusUpdateDTO {

    @NotBlank(message = "Status is required")
    private String status; // PENDING | APPROVED | REJECTED | SENT
}

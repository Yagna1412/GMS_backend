package com.gms.backend.technician.partsrequest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AllocatedPartCreateDto {

    @NotBlank(message = "Job card ID is required")
    private String jobCardId;

    @NotBlank(message = "Part name is required")
    private String partName;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private String status; // optional, defaults to ISSUED
}
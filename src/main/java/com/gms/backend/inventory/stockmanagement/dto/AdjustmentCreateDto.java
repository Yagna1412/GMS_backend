package com.gms.backend.inventory.stockmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdjustmentCreateDto {


    @NotNull(message = "Item is required")
    private Long itemId; // for client dropdown — send the numeric itemId


    @NotNull(message = "Quantity is required")
    private Integer quantity;               // negative allowed — e.g. -2 for damage

    @NotBlank(message = "Reference number is required")
    private String referenceId;

    @NotBlank(message = "Reason is mandatory for adjustments")
    private String notes;
}
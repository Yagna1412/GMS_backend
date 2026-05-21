package com.gms.backend.inventory.stockmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IssuePartsCreateDto {
    @NotNull(message = "Item is required")
    private Long itemId;



    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotBlank(message = "Reference number (Job Card Number) is required")
    private String referenceId;

    private String notes;
}
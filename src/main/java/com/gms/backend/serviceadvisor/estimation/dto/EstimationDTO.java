package com.gms.backend.serviceadvisor.estimation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstimationDTO {

    private Long id;
    private String estimationId;           // auto-generated — read only

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Job card ID is required")
    private String jobCardId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be > 0")
    private BigDecimal amount;

    @NotNull(message = "Discount is required")
    @DecimalMin(value = "0.0", message = "Discount cannot be negative")
    @DecimalMax(value = "100.0", message = "Discount cannot exceed 100%")
    private BigDecimal discount;

    private String status;

    @NotNull(message = "Valid till date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validTill;

    private String notes;

    @JsonFormat(pattern = "dd/MM/yyyy, HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy, HH:mm:ss")
    private LocalDateTime updatedAt;
}

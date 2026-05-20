package com.gms.backend.inventory.stockmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class StockMovementDto {
    private Long    id;
    private String  itemSku;
    private String  itemName;
    private Integer quantity;
    private String  movementType;
    private String  referenceId;
    private String  createdBy;
    private String  notes;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm a")
    private LocalDateTime dateTime;
}
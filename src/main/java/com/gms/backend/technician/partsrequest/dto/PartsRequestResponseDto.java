package com.gms.backend.technician.partsrequest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PartsRequestResponseDto {

    private Long    id;
    private String  requestId;
    private String  jobCardId;
    private String  partName;
    private Integer quantity;
    private String  type;
    private String  status;
    private String  reason;

    @JsonFormat(pattern = "dd/MM/yyyy, HH:mm:ss")
    private LocalDateTime requestedAt;
}
package com.gms.backend.technician.partsrequest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartsRequestSummaryDto {
    private long pending;    // lowercase → JSON: "pending"
    private long approved;   // lowercase → JSON: "approved"
    private long received;   // lowercase → JSON: "received"
}
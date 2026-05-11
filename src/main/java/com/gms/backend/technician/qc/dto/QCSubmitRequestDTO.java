package com.gms.backend.technician.qc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QCSubmitRequestDTO {

    private Long jobCardId;
    private boolean checklistCompleted;
}

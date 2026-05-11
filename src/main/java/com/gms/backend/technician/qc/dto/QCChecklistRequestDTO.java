package com.gms.backend.technician.qc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QCChecklistRequestDTO {

    private Long jobCardId;
    private List<QCChecklistDTO> items;
}

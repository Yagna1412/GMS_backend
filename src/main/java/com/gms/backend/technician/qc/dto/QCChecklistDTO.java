package com.gms.backend.technician.qc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QCChecklistDTO {

    private String name;
    private boolean completed;


}

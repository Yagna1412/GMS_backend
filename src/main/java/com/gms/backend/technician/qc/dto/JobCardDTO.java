package com.gms.backend.technician.qc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobCardDTO {

    private Long jobCardId;
    private String jobCardNumber;
    private String vehicleName;
}

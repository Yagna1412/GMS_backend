package com.gms.backend.hr.grievanceandexit.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ExitRequestDto {



    private String employeeId;
    private String employeeName;
    private String reason;
    private LocalDate lastWorkingDate;
}

package com.gms.backend.hr.grievanceandexit.dto;

import lombok.Data;

@Data
public class GrievanceRequestDto {


    private String employeeId;
    private String employeeName;
    private String category;
    private String severity;
    private String description;


}

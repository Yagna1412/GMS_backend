package com.gms.backend.jobcard.myjobs.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class JobDetailsDTO {

    private Long id;
    private String status;
    private String location;
    private LocalDate date;
    private String customer;
    private String vehicle;
    private String carNumber;
    private String mechanic;
    private Double amount;
    private String services;
}
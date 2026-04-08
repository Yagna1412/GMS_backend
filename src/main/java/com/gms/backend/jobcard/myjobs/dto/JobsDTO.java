package com.gms.backend.jobcard.myjobs.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class JobsDTO {
    private Long id;
    private String status;
    private LocalDate date;
    private String location;
    private Double amount;
    private String services;
}
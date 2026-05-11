package com.gms.backend.serviceadvisor.jobcard.dto;

import lombok.Data;

@Data
public class JobCardRequestDto {

    private String customerId;
    private String customerName;
    private String vehicle;
    private String customerComplaints;
    private Integer odometerReading;
    private String priority;
    private String technicianName;
}
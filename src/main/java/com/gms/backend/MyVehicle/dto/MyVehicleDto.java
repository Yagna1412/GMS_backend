package com.gms.backend.MyVehicle.dto;

import lombok.Data;

@Data
public class MyVehicleDto {

    private Long id;
    private Long customerId;
    private String make;
    private String model;
    private int year;
    private String fuelType;
    private String plateNo;
    private Boolean isPrimary;
    private String lastServiceDate;
    private String nextDueDate;
}
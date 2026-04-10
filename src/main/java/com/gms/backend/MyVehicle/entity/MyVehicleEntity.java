package com.gms.backend.MyVehicle.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vehicles")
@Data
public class MyVehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
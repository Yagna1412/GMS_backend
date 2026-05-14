package com.gms.backend.customer.myvehicle.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vehicles")
@Data
public class MyVehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "fuel_type")
    private String fuelType;

    // IMPORTANT: Explicitly map to plate_no column
    @Column(name = "plate_no")
    private String plateNo;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @Column(name = "last_service_date")
    private String lastServiceDate;

    @Column(name = "next_due_date")
    private String nextDueDate;
}
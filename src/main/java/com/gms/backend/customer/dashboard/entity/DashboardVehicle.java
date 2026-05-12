package com.gms.backend.customer.dashboard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DashboardVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private int year;

    @Column(name = "plate_no", unique = true)
    private String plateNo;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private DashboardCustomer customer;
}
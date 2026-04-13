package com.gms.backend.vehicle;

import com.gms.backend.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make; // e.g., Toyota [cite: 14]
    private String model; // e.g., Camry [cite: 15]
    private int year; // [cite: 16]

    @Column(name = "plate_no", unique = true)
    private String plateNo; // [cite: 18]

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer; // [cite: 13, 49]
}
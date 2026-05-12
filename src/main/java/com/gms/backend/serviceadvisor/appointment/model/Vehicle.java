package com.gms.backend.serviceadvisor.appointment.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "registration_number")
    private String registrationNumber;

    private String brand;

    private String model;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
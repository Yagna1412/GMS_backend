package com.gms.backend.serviceadvisor.appointment.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceName;
    private Double price;
    private String estimatedDuration;
}
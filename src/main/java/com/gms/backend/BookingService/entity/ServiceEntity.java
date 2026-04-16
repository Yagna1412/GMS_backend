package com.gms.backend.BookingService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "services")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long id;

    private String serviceName;
    private Double servicePrice;
    private Integer serviceDuration;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
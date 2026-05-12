package com.gms.backend.serviceadvisor.appointment.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "technicians")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Technician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String specialization;
    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
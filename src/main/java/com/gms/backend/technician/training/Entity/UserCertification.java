package com.gms.backend.technician.training.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "user_certifications")
@Data
public class UserCertification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long certificationId;

    private LocalDate issueDate;
    private LocalDate expiryDate;
}
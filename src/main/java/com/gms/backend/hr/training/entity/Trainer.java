package com.gms.backend.hr.training.entity;


import jakarta.persistence.*;
        import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "trainers")
@Data
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainerName;

    private String trainerType;

    private String organization;

    private String email;

    private String phone;

    private String specialization;
    private LocalDateTime createdAt;
}
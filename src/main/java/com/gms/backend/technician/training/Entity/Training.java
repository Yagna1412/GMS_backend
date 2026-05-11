package com.gms.backend.technician.training.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "trainings")
@Data
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String code;
}
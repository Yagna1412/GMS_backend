package com.gms.backend.technician.training.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "certifications")
@Data
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
}
package com.gms.backend.technician.training.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "training_materials")
@Data
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long trainingId;

    private String type; // VIDEO, PDF, QUIZ
    private String title;
    private String url;
}
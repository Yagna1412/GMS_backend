package com.gms.backend.technician.training.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "user_trainings")
@Data
public class UserTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long trainingId;

    private String status;
    private Integer progress;

    private LocalDate dueDate;
    private LocalDate completionDate;
}
package com.gms.backend.hr.training.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "training_feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔄 REFACTORED TO OBJECT RELATIONSHIP
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "training_program_id", referencedColumnName = "id")
    private TrainingProgram trainingProgram;

    @Column(name = "employee_id")
    private Long employeeId;

    private Integer rating;
    private String comments;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    // 🔄 REFACTORED TO OBJECT RELATIONSHIP
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;
}
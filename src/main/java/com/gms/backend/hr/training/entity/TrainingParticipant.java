package com.gms.backend.hr.training.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "training_participants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔄 REFACTORED TO TRUE JPA OBJECT RELATIONSHIP
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "training_program_id", referencedColumnName = "id")
    private TrainingProgram trainingProgram;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "attendance_status")
    private String attendanceStatus;

    @Column(name = "completion_status")
    private String completionStatus;

    @Column(name = "feedback_rating")
    private Integer feedbackRating;

    // 🔄 REFACTORED TO TRUE JPA OBJECT RELATIONSHIP
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @Column(name = "attendance_percentage")
    private Double attendancePercentage;

    @Column(name = "training_id")
    private Long trainingId;
}
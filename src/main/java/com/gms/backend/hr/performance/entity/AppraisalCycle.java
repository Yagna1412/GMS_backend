package com.gms.backend.hr.performance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "appraisal_cycles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppraisalCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cycleId;

    private String cycleName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private LocalDateTime createdAt;
}
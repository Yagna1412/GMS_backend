package com.gms.backend.hr.grievanceandexit.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="grievances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String grievanceCode;
    private String employeeId;

    private String employeeName;
    private String category;
    private String severity;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String status;

    private Integer slaDays;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

package com.gms.backend.hr.grievanceandexit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_exits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeExit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;

    private String employeeName;

    private String reason;

    private LocalDate lastWorkingDate;

    private String exitStatus;

    private LocalDateTime createdAt;

}

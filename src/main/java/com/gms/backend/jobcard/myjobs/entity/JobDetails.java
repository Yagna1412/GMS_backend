package com.gms.backend.jobcard.myjobs.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "job_details")
@Data
public class JobDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String status;
    private String location;
    private LocalDate date;
    private String customer;
    private String vehicle;
    private String carNumber;
    private String mechanic;
    private Double amount;
    private String services;
}
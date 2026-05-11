package com.gms.backend.serviceadvisor.communication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_card")
@Getter
@Setter
public class JobCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobCardNumber;
    private Long customerId;
    private String status;

    private LocalDateTime createdAt;
}
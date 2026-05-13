package com.gms.backend.serviceadvisor.dashboard.entity;

import com.gms.backend.serviceadvisor.dashboard.enums.JobCardStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_cards")
@Getter
@Setter
public class JobCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_card_number", nullable = false, unique = true)
    private String jobCardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private JobCardStatus status;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "completed_on")
    private LocalDateTime completedOn;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
}

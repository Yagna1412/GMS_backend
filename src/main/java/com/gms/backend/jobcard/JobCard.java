package com.gms.backend.jobcard;

import com.gms.backend.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "job_cards")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class JobCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String status;

    private String serviceType;

    private String branch;

    private double amount; // ✅ FIXED

    private LocalDate date;
}
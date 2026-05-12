package com.gms.backend.customer.dashboard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DashboardBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private DashboardCustomer customer;

    private String status;

    @Column(name = "scheduled_date")
    private LocalDate scheduledDate;

    private String branch;
}
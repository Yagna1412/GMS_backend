package com.gms.backend.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "service_history")
public class ServiceHistory {

    // ===== GETTERS =====
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name")   //
    private String serviceName;

    @Column(name = "service_date")   //
    private LocalDate serviceDate;

    @Column(name = "branch")
    private String branch;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "rating")
    private Integer rating;

    // ===== SETTERS =====
    public void setId(Long id) { this.id = id; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public void setServiceDate(LocalDate serviceDate) { this.serviceDate = serviceDate; }
    public void setBranch(String branch) { this.branch = branch; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setRating(Integer rating) { this.rating = rating; }
}
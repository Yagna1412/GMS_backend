package com.gms.backend.serviceadvisor.estimation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "estimations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estimation {

    public enum Status {
        PENDING, APPROVED, REJECTED, SENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String estimationId;           // EST/MUM/2024/0045

    @Column(nullable = false)
    private String customerName;           // Rahul Sharma

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private String jobCardId;              // JC/MUM/2024/0098

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;             // 7542.00

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal discount;
    // 10.00 (percentage)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal finalAmount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Status status = Status.PENDING;

    @Column(nullable = false)
    private LocalDate validTill;           // 2024-12-26

    @Column(length = 1000)
    private String notes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}


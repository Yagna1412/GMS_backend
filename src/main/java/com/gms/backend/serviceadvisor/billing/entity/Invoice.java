package com.gms.backend.serviceadvisor.billing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceId;     // INV/MUM/2024/0089
    private String jobCard;       // JC/MUM/2024/0097
    private String customerName;

    private Double amount;

    private String paymentStatus;   // PAID / PENDING
    private String deliveryStatus;  // READY / DELIVERED

    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
}

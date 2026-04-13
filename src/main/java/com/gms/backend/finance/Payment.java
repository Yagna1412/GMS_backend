package com.gms.backend.finance;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private String status;

    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
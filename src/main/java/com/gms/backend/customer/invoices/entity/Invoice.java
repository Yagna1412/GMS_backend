package com.gms.backend.customer.invoices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;

    // Customer Details
    private String customerName;
    private String customerAddress;
    private String customerPhone;

    // Service Details
    private String serviceType;
    private String serviceLocation;
    private String serviceDate;
    private String serviceTime;

    // Charges
    private Double serviceCharge;
    private Double laborCharge;
    private Double totalAmount;


    // Payment Details
    private String paymentMethod;
    private String paymentDate;
    private String status;
}
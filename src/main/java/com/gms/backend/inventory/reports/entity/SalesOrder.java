package com.gms.backend.inventory.reports.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sales_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    private String status;
}

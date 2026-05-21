package com.gms.backend.inventory.reports.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "stock_batches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "batch_number")
    private String batchNumber;

    @Column(name = "received_date")
    private LocalDate receivedDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    private Integer quantity;

    @Column(name = "remaining_quantity")
    private Integer remainingQuantity;

    @Column(name = "unit_cost")
    private BigDecimal unitCost;
}

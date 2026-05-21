package com.gms.backend.inventory.reports.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sales_order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sales_order_id")
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer quantity;

    @Column(name = "selling_price")
    private BigDecimal sellingPrice;
}

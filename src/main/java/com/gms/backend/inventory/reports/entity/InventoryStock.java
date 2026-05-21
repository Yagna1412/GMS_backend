package com.gms.backend.inventory.reports.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "current_stock")
    private Integer currentStock;

    @Column(name = "reserved_stock")
    private Integer reservedStock;

    @Column(name = "available_stock")
    private Integer availableStock;
}

package com.gms.backend.inventory.dashboard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private String itemName;

    private String imageUrl;

    private Integer currentStock;

    private Integer maxStock;

    private Integer reorderLevel;

    private String stockStatus;
}
package com.gms.backend.inventory.dashboard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itemName;

    private String skuCode;

    private Integer currentStock;

    private Integer minimumStock;

    private Integer reorderLevel;

    private Integer maximumStock;

    private BigDecimal unitPrice;

    private BigDecimal sellingPrice;

    private String itemImage;

    private String status;
}

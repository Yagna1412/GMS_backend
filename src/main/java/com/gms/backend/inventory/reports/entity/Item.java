package com.gms.backend.inventory.reports.entity;

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
    private Long id;

    private String sku;

    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String description;

    @Column(name = "cost_price")
    private BigDecimal costPrice;

    @Column(name = "selling_price")
    private BigDecimal sellingPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "reorder_level")
    private Integer reorderLevel;
}

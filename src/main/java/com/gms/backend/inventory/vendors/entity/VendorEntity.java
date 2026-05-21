package com.gms.backend.inventory.vendors.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="vendors")
@Data
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vendor_code", unique = true)
    private String vendorCode;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "phone")
    private String phone;

    private String email;

    private String address;

    @Column(name = "gst_number")
    private String gstNumber;

    @Column(name = "payment_terms")
    private String paymentTerms;

    private String tier;

    private String status;

    @Column(name = "total_pos")
    private Integer totalPos = 0;

    @Column(name = "on_time_percentage")
    private Double onTimePercentage = 0.0;

    private Double rating = 0.0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}



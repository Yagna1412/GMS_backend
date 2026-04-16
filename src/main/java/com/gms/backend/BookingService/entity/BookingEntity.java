package com.gms.backend.BookingService.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "branch_booking")
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    //  FIXED (use relation instead of branchId)
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    private Double totalPrice;
    private String bookingStatus;

    private LocalDateTime createdAt;
    private LocalDateTime bookingTime;

    // MANY TO MANY
    @ManyToMany
    @JoinTable(
            name = "booking_services",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<ServiceEntity> services;
}
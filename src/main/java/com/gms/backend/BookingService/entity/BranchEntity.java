package com.gms.backend.BookingService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "branch")
@NoArgsConstructor
@AllArgsConstructor
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long id;

    private String branchName;
    private String branchAddress;
    private String branchCity;
    private String branchZipcode;
    private Double branchRating;
    private Double branchLatitude;
    private Double branchLongitude;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
package com.gms.backend.customer.dashboard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DashboardCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "loyalty_pts")
    private Integer loyaltyPoints;
}
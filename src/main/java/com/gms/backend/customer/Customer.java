package com.gms.backend.customer;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // Matches your MySQL table name exactly
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Customer {
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
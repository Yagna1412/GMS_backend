package com.gms.backend.customer.profile.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private long mobile;
    private String address;
private String password;
    private boolean twoFactorEnabled;
    private String profileImagePath;


}
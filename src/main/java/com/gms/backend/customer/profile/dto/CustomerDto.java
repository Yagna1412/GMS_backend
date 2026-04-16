package com.gms.backend.customer.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String username;
    private String email;
    private long mobile;
    private String address;
    private boolean twoFactorEnabled;
}

package com.gms.backend.customer.profile.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProfile {

    private String username;
    private String email;
    private long mobile;
    private String address;
}
// RecentServiceDTO.java
package com.gms.backend.customer.dashboard.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentServiceDTO {
    private String date;
    private String service;
    private String branch;
    private double amount;
    private String status;
}
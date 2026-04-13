package com.gms.backend.customer.DashboardDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private String model;
    private String number; // Maps to plateNo in response
}
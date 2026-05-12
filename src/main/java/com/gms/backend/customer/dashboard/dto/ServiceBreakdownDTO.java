package com.gms.backend.customer.dashboard.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBreakdownDTO {
    private String type;
    private int count;
}
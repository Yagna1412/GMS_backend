package com.gms.backend.inventory.vendors.dto;

import lombok.Data;

@Data

public class VendorResponseDto {
    private Long id;

    private String vendorCode;

    private String vendorName;

    private String phone;

    private String email;

    private Double onTimePercentage;

    private Double rating;

    private Integer totalPos;

    private String tier;

    private String status;
}

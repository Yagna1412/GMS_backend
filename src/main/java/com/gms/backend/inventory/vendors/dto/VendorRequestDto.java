package com.gms.backend.inventory.vendors.dto;

import lombok.Data;

@Data

public class VendorRequestDto {
    private String vendorName;

    private String phone;

    private String email;

    private String address;

    private String gstNumber;

    private String paymentTerms;

    private String tier;

    private String status;


}

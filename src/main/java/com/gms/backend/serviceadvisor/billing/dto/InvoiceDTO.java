package com.gms.backend.serviceadvisor.billing.dto;

import lombok.Data;

@Data
public class InvoiceDTO {
    private String invoiceId;
    private String jobCard;
    private String customerName;
    private Double amount;
    private String paymentStatus;
    private String deliveryStatus;
}

package com.gms.backend.customer.invoices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class InvoiceListDto {

        private Long id;

        private String invoiceNumber;
        private String serviceDate;
        private Double totalAmount;
        private String status;

    }


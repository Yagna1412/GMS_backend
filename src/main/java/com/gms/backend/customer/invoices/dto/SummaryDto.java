package com.gms.backend.customer.invoices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SummaryDto {
    private Double totalDue;
    private Double totalPaid;
    private Double credit;



}
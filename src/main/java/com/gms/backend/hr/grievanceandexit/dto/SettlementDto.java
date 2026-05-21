package com.gms.backend.hr.grievanceandexit.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class SettlementDto {


    private BigDecimal salary;
    private BigDecimal bonus;
    private BigDecimal deductions;
}

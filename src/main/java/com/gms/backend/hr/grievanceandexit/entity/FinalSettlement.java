package com.gms.backend.hr.grievanceandexit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "final_settlements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalSettlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long exitId;

    private BigDecimal salary;

    private BigDecimal bonus;

    private BigDecimal deductions;

    private BigDecimal finalAmount;


}

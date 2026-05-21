package com.gms.backend.inventory.dashboard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "stock_audits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    private String auditName;

    private String status;

    private LocalDate auditDate;
}

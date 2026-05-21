package com.gms.backend.inventory.reports.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "report_email_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportEmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(name = "report_type")
    private String reportType;

    @Column(name = "sent_status")
    private String sentStatus;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;
}

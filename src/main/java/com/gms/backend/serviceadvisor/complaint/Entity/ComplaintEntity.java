package com.gms.backend.serviceadvisor.complaint.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintEntity {

    @Id
    @Column(name = "complaint_id", nullable = false, length = 30)
    private String complaintId;

    @Column(name = "complaint_number", unique = true)
    private String complaintNumber;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "severity", length = 20)
    private String severity;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "filed_on")
    private LocalDate filedOn;
}
package com.gms.backend.serviceadvisor.dashboard.entity;

import com.gms.backend.serviceadvisor.dashboard.enums.ComplaintPriority;
import com.gms.backend.serviceadvisor.dashboard.enums.ComplaintStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "complaints")
@Getter
@Setter
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complaint_type", nullable = false)
    private String complaintType;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private ComplaintPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ComplaintStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

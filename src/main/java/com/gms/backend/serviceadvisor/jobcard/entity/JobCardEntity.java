package com.gms.backend.serviceadvisor.jobcard.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "create_jobcard")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="job_card_id", unique = true)
    private String jobCardId;

    @Column(name = "customer_id", length = 50)
    private String customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "vehicle_details", nullable = false)
    private String vehicle;

    @Column(name = "customer_complaints", columnDefinition = "TEXT")
    private String customerComplaints;


    @Column(name = "odometer_reading")
    private Integer odometerReading;

    private String priority;

    @Column(name = "technician_name")
    private String technicianName;

    private String status;
    private Integer progress;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
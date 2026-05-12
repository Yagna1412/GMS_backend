package com.gms.backend.serviceadvisor.appointment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "Customer is required")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    @NotNull(message = "Vehicle is required")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date cannot be in the past")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;

    @NotBlank(message = "Duration is required")
    @Size(max = 50, message = "Duration must be less than 50 characters")
    private String duration;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.Scheduled;

    @Size(max = 1000, message = "Notes must be less than 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
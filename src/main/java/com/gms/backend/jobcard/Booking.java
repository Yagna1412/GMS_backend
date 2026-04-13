package com.gms.backend.jobcard;

import com.gms.backend.customer.Customer;
import com.gms.backend.vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String status;

    @Column(name = "scheduled_date")
    private LocalDate scheduledDate;

    // ✅ NEW
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private String branch;
}
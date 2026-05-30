//package com.gms.backend.customer.dashboard.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "job_cards")
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor
//public class DashboardJobCard {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private DashboardCustomer customer;
//
//    private String status;
//
//    private String serviceType;
//
//    private String branch;
//
//    private double amount;
//
//    private LocalDate date;
//}

package com.gms.backend.customer.dashboard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "job_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardJobCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private DashboardCustomer customer;

    private String status;

    @Column(name = "service_type")
    private String serviceType;

    private double amount;

    private LocalDate date;
}
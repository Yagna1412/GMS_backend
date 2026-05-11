package com.gms.backend.technician.qc.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="job_card")
@NoArgsConstructor
@AllArgsConstructor
public class JobCard {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "job_card_number")
    private String jobCardNumber;
    @Column(name = "vehicle_name")
    private String VehicleName;
    private String status;
}

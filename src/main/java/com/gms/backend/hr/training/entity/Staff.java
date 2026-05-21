package com.gms.backend.hr.training.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "staff")
@Data
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "employee_name")
//    private String employeeName;

    @Column(name = "full_name")
    private String fullName;

    private String department;

    private String designation;

    private String email;

    private String phone;

    private String status;

    private String password = "123456";

    private String role = "EMPLOYEE";
}
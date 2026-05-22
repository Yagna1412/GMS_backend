package com.gms.backend.hr.employeemaster.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class EmployeeMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String fullName;

    @Column(name = "employee_code", nullable = false)
    private String employeeCode;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "shift_name")
    private String shiftName;
    @Column(name = "designation")
    private String designation;

    @Column(name = "department")
    private String department;
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phoneNumber;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "address")
    private String address;

    private Long designationId;
    private Long departmentId;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "reporting_manager_id")
    private Long reportingManagerId;

    @Column(name = "joining_date")
    private String joiningDate;

    @Column(name = "basic_salary")
    private Double monthlySalary;

    @Column(name = "status")
    private String status;
}
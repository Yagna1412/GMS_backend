package com.gms.backend.hr.employeemaster.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeMasterRequestDto {

    private String fullName;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String shiftName;
    private String designation;

    private String department;
    private String email;

    private String phoneNumber;

    private String emergencyContact;

    private String address;

    private Long designationId;

    private Long departmentId;

    private Long branchId;

    private Long reportingManagerId;

    private String joiningDate;

    private Double monthlySalary;

    private String status;
}
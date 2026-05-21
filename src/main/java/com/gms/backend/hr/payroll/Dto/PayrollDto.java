package com.gms.backend.hr.payroll.Dto;

public class PayrollDto {

    private Long employeeId;
    private String month;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
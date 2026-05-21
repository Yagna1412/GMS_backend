package com.gms.backend.hr.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

public class PayrollDTO {
    private Long id;
    private Long employeeId;
    private LocalDate payrollMonth;
    private BigDecimal basicSalary;
    private BigDecimal allowances;
    private BigDecimal deductions;
    private BigDecimal netSalary;
    private String status;
    private LocalDate processedDate;

    public PayrollDTO() {}

    public PayrollDTO(Long employeeId, LocalDate payrollMonth, BigDecimal basicSalary) {
        this.employeeId = employeeId;
        this.payrollMonth = payrollMonth;
        this.basicSalary = basicSalary;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public LocalDate getPayrollMonth() { return payrollMonth; }
    public void setPayrollMonth(LocalDate payrollMonth) { this.payrollMonth = payrollMonth; }

    public BigDecimal getBasicSalary() { return basicSalary; }
    public void setBasicSalary(BigDecimal basicSalary) { this.basicSalary = basicSalary; }

    public BigDecimal getAllowances() { return allowances; }
    public void setAllowances(BigDecimal allowances) { this.allowances = allowances; }

    public BigDecimal getDeductions() { return deductions; }
    public void setDeductions(BigDecimal deductions) { this.deductions = deductions; }

    public BigDecimal getNetSalary() { return netSalary; }
    public void setNetSalary(BigDecimal netSalary) { this.netSalary = netSalary; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getProcessedDate() { return processedDate; }
    public void setProcessedDate(LocalDate processedDate) { this.processedDate = processedDate; }
}

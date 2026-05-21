package com.gms.backend.hr.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "payroll")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;
    
    @Column(name = "payroll_month")
    private LocalDate payrollMonth;
    
    @Column(name = "basic_salary", precision = 10, scale = 2)
    private BigDecimal basicSalary;
    
    @Column(name = "allowances", precision = 10, scale = 2)
    private BigDecimal allowances;
    
    @Column(name = "deductions", precision = 10, scale = 2)
    private BigDecimal deductions;
    
    @Column(name = "net_salary", precision = 10, scale = 2)
    private BigDecimal netSalary;
    
    @Column(name = "status") // PENDING, PROCESSED, PAID
    private String status;
    
    @Column(name = "processed_date")
    private LocalDate processedDate;

    public Payroll() {}

    public Payroll(Long employeeId, LocalDate payrollMonth, BigDecimal basicSalary) {
        this.employeeId = employeeId;
        this.payrollMonth = payrollMonth;
        this.basicSalary = basicSalary;
        this.status = "PENDING";
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

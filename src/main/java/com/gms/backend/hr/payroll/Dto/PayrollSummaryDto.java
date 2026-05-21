package com.gms.backend.hr.payroll.Dto;

public class PayrollSummaryDto {

    private long totalEmployees;
    private double totalSalary;
    private String processingStatus;

    public PayrollSummaryDto(long totalEmployees,
                             double totalSalary,
                             String processingStatus) {
        this.totalEmployees = totalEmployees;
        this.totalSalary = totalSalary;
        this.processingStatus = processingStatus;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public String getProcessingStatus() {
        return processingStatus;
    }
}
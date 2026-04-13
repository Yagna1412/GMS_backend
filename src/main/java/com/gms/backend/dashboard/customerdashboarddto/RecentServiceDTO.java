package com.gms.backend.dashboard.customerdashboarddto;

public class RecentServiceDTO {

    private String date;
    private String serviceType;
    private String branch;
    private double amount;
    private String status;

    public RecentServiceDTO(String date, String serviceType, String branch, double amount, String status) {
        this.date = date;
        this.serviceType = serviceType;
        this.branch = branch;
        this.amount = amount;
        this.status = status;
    }

    public String getDate() { return date; }
    public String getServiceType() { return serviceType; }
    public String getBranch() { return branch; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
}
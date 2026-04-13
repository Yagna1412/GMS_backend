package com.gms.backend.customer.dto;

import java.time.LocalDate;

public class ServiceHistoryDTO {

    private Long id;
    private String serviceName;
    private String branch;
    private Double amount;
    private Integer rating;
    private LocalDate serviceDate;

    // Constructor
    public ServiceHistoryDTO(Long id, String serviceName, String branch,
                             Double amount, Integer rating, LocalDate serviceDate) {
        this.id = id;
        this.serviceName = serviceName;
        this.branch = branch;
        this.amount = amount;
        this.rating = rating;
        this.serviceDate = serviceDate;
    }

    // getters
    public Long getId() { return id; }
    public String getServiceName() { return serviceName; }
    public String getBranch() { return branch; }
    public Double getAmount() { return amount; }
    public Integer getRating() { return rating; }
    public LocalDate getServiceDate() { return serviceDate; }

    //  Setters
    public void setId(Long id) { this.id = id; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public void setBranch(String branch) { this.branch = branch; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setRating(Integer rating) { this.rating = rating; }
    public void setServiceDate(LocalDate serviceDate) { this.serviceDate = serviceDate; }
}
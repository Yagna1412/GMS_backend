package com.gms.backend.serviceadvisor.customers.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "add_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_id")
    private Long customerId;
    private String make;
    private String model;
    @Column(name="manufacturing_year")
    private String year;
    @Column(name="registration_number")
    private String regNo;
    private String vin;
    @Column(name="added_at")
    private Date addedAt;

    public Vehicle() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }
}
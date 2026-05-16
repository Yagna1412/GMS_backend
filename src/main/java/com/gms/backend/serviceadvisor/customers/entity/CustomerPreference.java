package com.gms.backend.serviceadvisor.customers.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_preferences")
public class CustomerPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="customer_id")
    private Long customerId;
    @Column(name="preferred_technician")
    private String preferredTechnician;
    @Column(name="time_slot")
    private String timeSlot;
    @Column(name="communication_mode")
    private String communicationMode;
    @Column(name="preferred_service_time")
    private String preferredTime;
    @Column(name="special_instructions")
    private String specialInstructions;

    public CustomerPreference() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getCommunicationMode() { return communicationMode; }
    public void setCommunicationMode(String communicationMode) { this.communicationMode = communicationMode; }

    public String getSpecialInstructions() { return specialInstructions; }
    public void setSpecialInstructions(String specialInstructions) { this.specialInstructions = specialInstructions; }

    public String getPreferredTechnician() {
        return preferredTechnician;
    }

    public void setPreferredTechnician(String preferredTechnician) {
        this.preferredTechnician = preferredTechnician;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }
}
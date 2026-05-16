package com.gms.backend.serviceadvisor.customers.dto;

import lombok.Data;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.gms.backend.serviceadvisor.customers.entity.Customer;
import com.gms.backend.serviceadvisor.customers.entity.Vehicle;
import com.gms.backend.serviceadvisor.customers.entity.ServiceHistory;

public class CustomerDTO {

    @Data
    public static class CustomerRequest {

        @NotBlank(message = "Name is required")
        private String name;

        @NotBlank(message = "Phone is required")
        private String phone;

        @Email(message = "Invalid email")
        private String email;

        private String address;

        @NotBlank(message = "Customer type is required")
        private String type;
    }

    @Data
    public static class CustomerResponse {
        private Long id;
        private String name;
        private String phone;
        private String email;
        private String address;
        private String type;
    }

    @Data
    public static class CustomerStatsResponse {
        private Long totalCustomers;
        private Long regular;
        private Long premium;
        private Long vip;
    }

    @Data
    public static class VehicleRequest {

        @NotNull(message = "Customer ID is required")
        private Long customerId;

        @NotBlank(message = "Make is required")
        private String make;

        @NotBlank(message = "Model is required")
        private String model;

        private String year;

        @NotBlank(message = "Registration number is required")
        private String regNo;

        private String vin;
    }

    @Data
    public static class VehicleResponse {
        private Long id;
        private Long customerId;
        private String make;
        private String model;
        private String regNo;
    }

    @Data
    public static class ServiceHistoryResponse {
        private Long id;
        private String serviceName;
        private Double amount;
        private String status;
    }

    @Data
    public static class CustomerPreferenceRequest {

        @NotNull(message = "Customer ID is required")
        private Long customerId;

        private String preferredServiceTime;

        private String preferredTechnician;

        private String timeSlot;

        private String communicationMode;

        private String specialInstructions;
    }

    @Data
    public static class CustomerPreferenceResponse {
        private Long id;
        private Long customerId;
        private String preferredServiceTime;
        private String communicationMode;
        private String specialInstructions;
    }

    @Data
    public static class CustomerFullProfileResponse {
        private Customer customer;
        private List<Vehicle> vehicles;
        private List<ServiceHistory> serviceHistory;
    }
}
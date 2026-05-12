package com.gms.backend.serviceadvisor.complaint.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

public class ComplaintDto {

    @Data
    public static class RegisterRequest {

        @NotNull(message = "Customer ID is required")
        private Long customerId;

        @NotBlank(message = "Customer Name is required")
        private String customerName;

        private String category;    // Service Quality, Billing, etc.

        private String severity;    // Low / Medium / High

        @NotBlank(message = "Description is required")
        private String description;
    }

    @Data
    public static class StatusUpdateRequest {

        @NotBlank(message = "Status is required")
        private String status;      // Pending / Under Review / Resolved
    }

    @Data
    @Builder
    public static class ComplaintResponse {
        //private Long id;
        private String complaintId;
        private String customerId;
        private String customerName;
        private String category;
        private String severity;
        private String description;
        private String status;
        private LocalDate filedOn;
    }

    @Data
    @Builder
    public static class DashboardResponse {
        private long totalComplaints;
        private long pending;
        private long underReview;
        private long resolved;
    }
}
package com.gms.backend.serviceadvisor.jobcard.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobCardResponseDto {

    private Long id;
    private String jobCardId;
    private String customerId;
    private String customerName;
    private String vehicle;
    private String customerComplaints;
    private Integer odometerReading;
    private String priority;
    private String technicianName;
    private String status;
    private Integer progress;
    private LocalDateTime createdAt;
}
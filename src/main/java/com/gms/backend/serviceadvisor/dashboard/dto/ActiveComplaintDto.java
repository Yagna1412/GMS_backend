package com.gms.backend.serviceadvisor.dashboard.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveComplaintDto {
    private Long complaintId;
    private String customerName;
    private String type;
    private String priority;
    private String status;
}
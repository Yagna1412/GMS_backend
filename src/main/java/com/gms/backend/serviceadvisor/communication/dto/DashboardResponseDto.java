package com.gms.backend.serviceadvisor.communication.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponseDto {
    private long totalMessagesToday;
    private long whatsappCount;
    private long smsEmailCount;
}
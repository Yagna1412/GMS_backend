package com.gms.backend.serviceadvisor.communication.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommunicationListDto {
    private Long jobCardId;
    private String jobCardNumber;
    private String customerName;
    private String status;
    private String lastContact;
}

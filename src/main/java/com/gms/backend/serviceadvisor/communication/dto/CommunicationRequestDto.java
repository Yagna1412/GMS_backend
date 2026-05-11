package com.gms.backend.serviceadvisor.communication.dto;


import lombok.Data;

@Data
public class CommunicationRequestDto {
    private Long jobCardId;
    private String message;
    private String subject;
}
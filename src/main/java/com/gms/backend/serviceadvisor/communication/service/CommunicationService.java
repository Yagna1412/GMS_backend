package com.gms.backend.serviceadvisor.communication.service;


import com.gms.backend.serviceadvisor.communication.dto.*;
import java.util.List;

public interface CommunicationService {

    void sendWhatsApp(CommunicationRequestDto dto);
    void sendSMS(CommunicationRequestDto dto);
    void sendEmail(CommunicationRequestDto dto);
    void logCall(CommunicationRequestDto dto);

    DashboardResponseDto getDashboard();

    List<CommunicationListDto> getCommunicationList();
}
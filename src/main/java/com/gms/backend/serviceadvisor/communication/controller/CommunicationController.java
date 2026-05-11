package com.gms.backend.serviceadvisor.communication.controller;

import com.gms.backend.serviceadvisor.communication.dto.*;
import com.gms.backend.serviceadvisor.communication.service.CommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5177")
@RestController
@RequestMapping("/api/communications")
@RequiredArgsConstructor
public class CommunicationController {

    private final CommunicationService service;

    @PostMapping("/whatsapp")
    public ResponseEntity<String> sendWhatsApp(@RequestBody CommunicationRequestDto dto) {
        service.sendWhatsApp(dto);
        return ResponseEntity.ok("WhatsApp message logged successfully");
    }

    @PostMapping("/sms")
    public ResponseEntity<String> sendSMS(@RequestBody CommunicationRequestDto dto) {
        service.sendSMS(dto);
        return ResponseEntity.ok("SMS logged successfully");
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestBody CommunicationRequestDto dto) {
        service.sendEmail(dto);
        return ResponseEntity.ok("Email logged successfully");
    }

    @PostMapping("/call")
    public ResponseEntity<String> logCall(@RequestBody CommunicationRequestDto dto) {
        service.logCall(dto);
        return ResponseEntity.ok("Call log saved successfully");
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponseDto> getDashboard() {
        return ResponseEntity.ok(service.getDashboard());
    }

    @GetMapping
    public ResponseEntity<List<CommunicationListDto>> getCommunications() {
        return ResponseEntity.ok(service.getCommunicationList());
    }
}
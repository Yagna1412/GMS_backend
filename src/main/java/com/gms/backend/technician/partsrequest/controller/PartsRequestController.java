package com.gms.backend.technician.partsrequest.controller;

import com.gms.backend.technician.partsrequest.dto.*;
import com.gms.backend.technician.partsrequest.service.PartsRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts-requests")
@RequiredArgsConstructor
public class PartsRequestController {

    private final PartsRequestService service;

    // GET /api/parts-requests/summary
    // Powers: Pending / Approved / Received cards
    @GetMapping("/summary")
    public ResponseEntity<PartsRequestSummaryDto> getSummary() {
        return ResponseEntity.ok(service.getSummary());
    }

    // GET /api/parts-requests
    // Powers: table rows
    @GetMapping
    public ResponseEntity<List<PartsRequestResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAllRequests());
    }

    // POST /api/parts-requests
    // Powers: "+ Request Parts" modal submit
    @PostMapping
    public ResponseEntity<PartsRequestResponseDto> create(
            @Valid @RequestBody PartsRequestCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createRequest(dto));
    }

    // PUT /api/parts-requests/{id}/status
    // Powers: Approve / Reject action
    // Body: { "status": "APPROVED" }
    @PutMapping("/{id}/status")
    public ResponseEntity<PartsRequestResponseDto> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody StatusUpdateDto dto) {
        return ResponseEntity.ok(service.updateStatus(id, dto));
    }

    // PUT /api/parts-requests/{id}
    // Powers: Edit row action
    @PutMapping("/{id}")
    public ResponseEntity<PartsRequestResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody PartsRequestCreateDto dto) {
        return ResponseEntity.ok(service.updateRequest(id, dto));
    }

    // DELETE /api/parts-requests/{id}
    // Powers: Delete row action (PENDING only)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
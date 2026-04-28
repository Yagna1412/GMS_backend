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

    // 1. Summary cards — Pending / Approved / Received counts
    @GetMapping("/summary")
    public ResponseEntity<PartsRequestSummaryDto> getSummary() {
        return ResponseEntity.ok(service.getSummary());
    }

    // 2. Table — all parts requests
    @GetMapping
    public ResponseEntity<List<PartsRequestResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAllRequests());
    }

    // 3. "+ Request Parts" modal submit
    @PostMapping
    public ResponseEntity<PartsRequestResponseDto> create(
            @Valid @RequestBody PartsRequestCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createRequest(dto));
    }

    // 4. Status update (Approve / Reject action on row)
    @PutMapping("/{id}/status")
    public ResponseEntity<PartsRequestResponseDto> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody StatusUpdateDto dto) {
        return ResponseEntity.ok(service.updateStatus(id, dto));
    }

    // 5. Edit row action
    @PutMapping("/{id}")
    public ResponseEntity<PartsRequestResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody PartsRequestCreateDto dto) {
        return ResponseEntity.ok(service.updateRequest(id, dto));
    }

    // 6. Delete row action
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }


}
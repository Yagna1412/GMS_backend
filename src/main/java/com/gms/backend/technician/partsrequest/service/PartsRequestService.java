package com.gms.backend.technician.partsrequest.service;

import com.gms.backend.technician.partsrequest.dto.*;
import com.gms.backend.technician.partsrequest.entity.PartsRequest;
import com.gms.backend.technician.partsrequest.entity.PartsRequest.Status;
import com.gms.backend.technician.partsrequest.entity.PartsRequest.Type;
import com.gms.backend.technician.partsrequest.repository.PartsRequestRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartsRequestService {

    private final PartsRequestRepo partsRepo;

    // Generate Request ID: PR/MUM/2026/0048
    private String generateRequestId() {
        String year   = String.valueOf(LocalDate.now().getYear());
        String prefix = "PR/MUM/" + year + "/";

        Optional<String> latest = partsRepo.findMaxRequestIdForYear(prefix + "%");

        int nextSeq = 1;
        if (latest.isPresent()) {
            try {
                String[] parts = latest.get().split("/");
                nextSeq = Integer.parseInt(parts[parts.length - 1]) + 1;
            } catch (Exception e) {
                log.warn("Could not parse latest requestId '{}', starting from 1", latest.get());
            }
        }
        return prefix + String.format("%04d", nextSeq);
    }

    // Summary cards: Pending / Approved / Received
    @Transactional(readOnly = true)
    public PartsRequestSummaryDto getSummary() {
        Object[] result = partsRepo.getSummary();
        Object[] row    = (Object[]) result[0];
        long pending  = row[0] != null ? ((Number) row[0]).longValue() : 0L;
        long approved = row[1] != null ? ((Number) row[1]).longValue() : 0L;
        long received = row[2] != null ? ((Number) row[2]).longValue() : 0L;
        return new PartsRequestSummaryDto(pending, approved, received);
    }

    // Table: get all requests
    @Transactional(readOnly = true)
    public List<PartsRequestResponseDto> getAllRequests() {
        return partsRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // "+ Request Parts" modal: create
    @Transactional
    public PartsRequestResponseDto createRequest(PartsRequestCreateDto dto) {
        PartsRequest request = PartsRequest.builder()
                .requestId(generateRequestId())
                .jobCardId(dto.getJobCardId().trim().toUpperCase())
                .partName(dto.getPartName().trim())
                .quantity(dto.getQuantity())
                .reason(dto.getReason().trim())
                .type(dto.getType() != null
                        ? Type.valueOf(dto.getType().toUpperCase())
                        : Type.ADDITIONAL)
                .status(Status.Pending)
                .build();
        // requestedAt is set automatically by @PrePersist in entity

        PartsRequest saved = partsRepo.save(request);
        log.info("Parts request created: {}", saved.getRequestId());
        return toDto(saved);
    }

    // Edit row action: update details (PENDING only)
    @Transactional
    public PartsRequestResponseDto updateRequest(Long id, PartsRequestCreateDto dto) {
        PartsRequest request = findById(id);

        if (request.getStatus() != Status.Pending) {
            throw new IllegalStateException(
                    "Cannot edit a request with status: " + request.getStatus()
                            + ". Only PENDING requests can be edited.");
        }

        request.setJobCardId(dto.getJobCardId().trim().toUpperCase());
        request.setPartName(dto.getPartName().trim());
        request.setQuantity(dto.getQuantity());
        request.setReason(dto.getReason().trim());

        if (dto.getType() != null) {
            request.setType(Type.valueOf(dto.getType().toUpperCase()));
        }
        // requestedAt is NOT touched — it is a creation timestamp only

        PartsRequest saved = partsRepo.save(request);
        log.info("Parts request updated: {}", saved.getRequestId());
        return toDto(saved);
    }

    // Status action: Approve / Reject / Received
    @Transactional
    public PartsRequestResponseDto updateStatus(Long id, StatusUpdateDto dto) {
        PartsRequest request = findById(id);

        Status newStatus;
        try {
            newStatus = Status.valueOf(dto.getStatus().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Invalid status '" + dto.getStatus() +
                            "'. Allowed: PENDING, APPROVED, REJECTED, RECEIVED");
        }

        log.info("Status change: {} → {} → {}",
                request.getRequestId(), request.getStatus(), newStatus);

        request.setStatus(newStatus);
        return toDto(partsRepo.save(request));
    }

    // Delete row action (PENDING only)
    @Transactional
    public void deleteRequest(Long id) {
        PartsRequest request = findById(id);

        if (request.getStatus() != Status.Pending) {
            throw new IllegalStateException(
                    "Cannot delete a request with status: " + request.getStatus()
                            + ". Only PENDING requests can be deleted.");
        }

        partsRepo.deleteById(id);
        log.info("Parts request deleted: id={}", id);
    }

    // Private helpers
    private PartsRequest findById(Long id) {
        return partsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Parts request not found with id: " + id));
    }

    private PartsRequestResponseDto toDto(PartsRequest req) {
        return PartsRequestResponseDto.builder()
                .id(req.getId())
                .requestId(req.getRequestId())
                .jobCardId(req.getJobCardId())
                .partName(req.getPartName())
                .quantity(req.getQuantity())
                .type(req.getType()     != null ? req.getType().name()   : null)
                .status(req.getStatus() != null ? req.getStatus().name() : null)
                .reason(req.getReason())
                .requestedAt(req.getRequestedAt())
                .build();
    }
}
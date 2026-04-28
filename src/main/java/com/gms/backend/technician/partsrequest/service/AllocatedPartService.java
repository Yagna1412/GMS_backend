package com.gms.backend.technician.partsrequest.service;

import com.gms.backend.technician.partsrequest.dto.ActiveJobAllocationsDto;
import com.gms.backend.technician.partsrequest.dto.AllocatedPartResponseDto;
import com.gms.backend.technician.partsrequest.entity.AllocatedPart;
import com.gms.backend.technician.partsrequest.repository.AllocatedPartRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllocatedPartService {

    private final AllocatedPartRepo allocatedRepo;

    // ── "Allocated Parts (Active Jobs)" section ────────────────────────────
    @Transactional(readOnly = true)
    public List<ActiveJobAllocationsDto> getAllGroupedByJobCard() {

        List<String> jobCardIds = allocatedRepo.findDistinctJobCardIds();

        return jobCardIds.stream().map(jobCardId -> {

            List<AllocatedPartResponseDto> parts = allocatedRepo
                    .findByJobCardId(jobCardId)   // ✅ FIXED HERE
                    .stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());

            return new ActiveJobAllocationsDto(jobCardId, parts);

        }).collect(Collectors.toList());
    }

    // ── Mapper ────────────────────────────────────────────────────────────
    private AllocatedPartResponseDto toDto(AllocatedPart part) {
        return AllocatedPartResponseDto.builder()
                .id(part.getId())
                .jobCardId(part.getJobCardId())
                .partName(part.getPartName())
                .quantity(part.getQuantity())
                .status(part.getStatus() != null ? part.getStatus().name() : null)
                .build();
    }
}
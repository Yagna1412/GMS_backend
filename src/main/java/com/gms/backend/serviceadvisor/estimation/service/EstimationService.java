package com.gms.backend.serviceadvisor.estimation.service;

import com.gms.backend.serviceadvisor.estimation.dto.EstimationDTO;
import com.gms.backend.serviceadvisor.estimation.dto.EstimationSummaryDTO;
import com.gms.backend.serviceadvisor.estimation.entity.Estimation;
import com.gms.backend.serviceadvisor.estimation.repository.EstimationRepository;

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
public class EstimationService {

    private final EstimationRepository estimationRepo;

    // Generate Estimation ID
    private String generateEstimationId() {

        String year = String.valueOf(LocalDate.now().getYear());

        String prefix = "EST/MUM/" + year + "/";

        Optional<String> latestId =
                estimationRepo.findLatestEstimationIdForYear(prefix);

        int nextSeq = 1;

        if (latestId.isPresent()) {

            try {

                String[] parts = latestId.get().split("/");

                nextSeq =
                        Integer.parseInt(parts[parts.length - 1]) + 1;

            } catch (Exception e) {

                log.error("Error while parsing estimation ID", e);
            }
        }

        return prefix + String.format("%04d", nextSeq);
    }

    // Summary
    @Transactional(readOnly = true)
    public EstimationSummaryDTO getSummary() {

        return estimationRepo.getSummary();
    }

    // Get All
    @Transactional(readOnly = true)
    public List<EstimationDTO> getAllEstimations() {

        return estimationRepo.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Get By ID
    @Transactional(readOnly = true)
    public EstimationDTO getEstimationById(Long id) {

        return toDto(findById(id));
    }

    // Create
    @Transactional
    public EstimationDTO createEstimation(EstimationDTO dto) {

        Estimation estimation = new Estimation();

        estimation.setEstimationId(generateEstimationId());

        estimation.setCustomerName(dto.getCustomerName());

        estimation.setCustomerId(dto.getCustomerId());

        estimation.setJobCardId(dto.getJobCardId());

        estimation.setAmount(dto.getAmount());

        estimation.setDiscount(dto.getDiscount());

        estimation.setFinalAmount(
                dto.getAmount().subtract(dto.getDiscount())
        );

        estimation.setValidTill(dto.getValidTill());

        estimation.setNotes(dto.getNotes());

        estimation.setStatus(Estimation.Status.PENDING);

        Estimation saved = estimationRepo.save(estimation);

        return toDto(saved);
    }

    // Update
    @Transactional
    public EstimationDTO updateEstimation(Long id, EstimationDTO dto) {

        Estimation estimation = findById(id);

        if (estimation.getStatus() != Estimation.Status.PENDING) {

            throw new RuntimeException(
                    "Only PENDING estimation can be edited"
            );
        }

        estimation.setCustomerName(dto.getCustomerName().trim());
        estimation.setCustomerId(dto.getCustomerId());
        estimation.setJobCardId(dto.getJobCardId().trim().toUpperCase());
        estimation.setAmount(dto.getAmount());
        estimation.setDiscount(dto.getDiscount());
        estimation.setValidTill(dto.getValidTill());
        estimation.setNotes(dto.getNotes());

        return toDto(estimationRepo.save(estimation));
    }

    // Update Status
    @Transactional
    public EstimationDTO updateStatus(Long id, String status) {

        Estimation estimation = findById(id);

        try {

            estimation.setStatus(
                    Estimation.Status.valueOf(status.toUpperCase())
            );

        } catch (Exception e) {

            throw new RuntimeException("Invalid status");
        }

        return toDto(estimationRepo.save(estimation));
    }

    // Delete
    @Transactional
    public void deleteEstimation(Long id) {

        Estimation estimation = findById(id);

        if (estimation.getStatus() != Estimation.Status.PENDING) {

            throw new RuntimeException(
                    "Only PENDING estimation can be deleted"
            );
        }

        estimationRepo.delete(estimation);
    }

    // Helper Method
    private Estimation findById(Long id) {

        return estimationRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Estimation not found"));
    }

    // Entity -> DTO
    private EstimationDTO toDto(Estimation e) {

        return EstimationDTO.builder()
                .id(e.getId())
                .estimationId(e.getEstimationId())
                .customerName(e.getCustomerName())
                .customerId(e.getCustomerId())
                .jobCardId(e.getJobCardId())
                .amount(e.getAmount())
                .discount(e.getDiscount())
                .status(e.getStatus().name())
                .validTill(e.getValidTill())
                .notes(e.getNotes())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }
}
package com.gms.backend.technician.qc.service;

import com.gms.backend.technician.qc.dto.*;
import com.gms.backend.technician.qc.entity.JobCard;
import com.gms.backend.technician.qc.entity.QCChecklist;
import com.gms.backend.technician.qc.repository.JobCardRepository;
import com.gms.backend.technician.qc.repository.QCChecklistRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QCService {

    private final JobCardRepository jobRepo;
    private final QCChecklistRepository checklistRepo;

    // JSON converter
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 1. Dashboard
    public DashboardResponseDTO getDashboard() {

        long ready = jobRepo.countByStatus("READY_FOR_QC");

        long pending = checklistRepo.countByStatus("QC_PENDING");

        double total = ready + pending;

        double firstPassRate =
                (total == 0) ? 0 : (ready * 100.0) / total;

        return new DashboardResponseDTO(
                ready,
                pending,
                firstPassRate
        );
    }

    // 2. Ready Jobs
    public List<JobCardDTO> getReadyJobs() {

        return jobRepo.findByStatus("READY_FOR_QC")
                .stream()
                .map(j -> new JobCardDTO(
                        j.getId(),
                        j.getJobCardNumber(),
                        j.getVehicleName()
                ))
                .collect(Collectors.toList());
    }

    // 3. Save Checklist
    public void saveChecklist(QCChecklistRequestDTO request) {

        try {

            // Validation
            if (request.getJobCardId() == null) {
                throw new RuntimeException("JobCardId is required");
            }

            if (request.getItems() == null || request.getItems().isEmpty()) {
                throw new RuntimeException("Checklist items required");
            }

            // Delete old checklist
            checklistRepo.deleteByJobCardId(
                    (request.getJobCardId())
            );

            // Convert checklist to JSON string
            String checklistJson =
                    objectMapper.writeValueAsString(request.getItems());

            // Create QC record
            QCChecklist qc = new QCChecklist();

            qc.setJobCardId(request.getJobCardId());

            qc.setMechanicId("MEC001");

            qc.setChecklistData(checklistJson);

            qc.setMechanicNotes("QC checklist submitted");

            qc.setStatus("QC_PENDING");

            qc.setSubmittedAt(LocalDateTime.now());

            qc.setOrganizationId(1);

            qc.setBranchId(1);

            qc.setStaffId(1);

            checklistRepo.save(qc);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to save checklist : " + e.getMessage()
            );
        }
    }

    // 4. Get Checklist
    public List<QCChecklistDTO> getChecklist(Long jobId) {

        QCChecklist qc = checklistRepo
                .findByJobCardId(jobId);

        if (qc == null) {
            throw new RuntimeException("Checklist not found");
        }

        try {

            return objectMapper.readValue(
                    qc.getChecklistData(),
                    objectMapper.getTypeFactory()
                            .constructCollectionType(
                                    List.class,
                                    QCChecklistDTO.class
                            )
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error reading checklist"
            );
        }
    }

    // 5. Submit QC
    public void submitQC(QCSubmitRequestDTO request) {

        if (request.getJobCardId() == null) {
            throw new RuntimeException("JobCardId required");
        }

        QCChecklist qc = checklistRepo
                .findByJobCardId(
                        request.getJobCardId()
                );

        if (qc == null) {
            throw new RuntimeException("Checklist not found");
        }

        qc.setStatus("QC_SUBMITTED");

        checklistRepo.save(qc);

        JobCard job = jobRepo.findById(
                request.getJobCardId()
        ).orElseThrow(() ->
                new RuntimeException("Job not found")
        );

        job.setStatus("QC_PENDING");

        jobRepo.save(job);
    }
}
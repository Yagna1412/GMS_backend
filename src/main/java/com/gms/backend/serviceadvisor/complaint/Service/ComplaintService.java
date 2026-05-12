package com.gms.backend.serviceadvisor.complaint.Service;

import com.gms.backend.serviceadvisor.complaint.DTO.ComplaintDto;
import com.gms.backend.serviceadvisor.complaint.Entity.ComplaintEntity;
import com.gms.backend.serviceadvisor.complaint.Repo.ComplaintRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepo complaintRepo;

    // 1. Register Complaint
    public ComplaintDto.ComplaintResponse registerComplaint(ComplaintDto.RegisterRequest request) {
        String complaintId = generateComplaintId();

        ComplaintEntity entity = ComplaintEntity.builder()
                .complaintId(complaintId)
                .customerId(request.getCustomerId())
                .customerName(request.getCustomerName())
                .category(request.getCategory() != null ? request.getCategory() : "Service Quality")
                .severity(request.getSeverity() != null ? request.getSeverity() : "Low")
                .description(request.getDescription())
                .status("Pending")
                .filedOn(LocalDate.now())
                .build();

        return toResponse(complaintRepo.save(entity));
    }

    // 2. Dashboard Counts
    public ComplaintDto.DashboardResponse getDashboard() {
        return ComplaintDto.DashboardResponse.builder()
                .totalComplaints(complaintRepo.count())
                .pending(complaintRepo.countByStatus("Pending"))
                .underReview(complaintRepo.countByStatus("Under Review"))
                .resolved(complaintRepo.countByStatus("Resolved"))
                .build();
    }

    // 3. Get All Complaints
    public List<ComplaintDto.ComplaintResponse> getAllComplaints() {
        return complaintRepo.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // 4. Get By ID
    public ComplaintDto.ComplaintResponse getComplaintById(String complaintId) {
        return toResponse(findOrThrow(complaintId));
    }

    // 5. Update Status
    public ComplaintDto.ComplaintResponse updateStatus(String complaintId,
                                                       ComplaintDto.StatusUpdateRequest request) {
        ComplaintEntity entity = findOrThrow(complaintId);
        entity.setStatus(request.getStatus());
        return toResponse(complaintRepo.save(entity));
    }

    // 6. Delete Complaint
    public void deleteComplaint(String complaintId) {
        complaintRepo.delete(findOrThrow(complaintId));
    }

    // ── Helpers ──────────────────────────────────────────────────────────

    private ComplaintEntity findOrThrow(String complaintId) {
        return complaintRepo.findByComplaintId(complaintId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Complaint not found: " + complaintId));
    }

    private String generateComplaintId() {
        String year = String.valueOf(LocalDate.now().getYear());
        long seq = complaintRepo.count() + 1;
        String id = "COMP/MUM/" + year + "/" + String.format("%03d", seq);
        while (complaintRepo.existsByComplaintId(id)) {
            id = "COMP/MUM/" + year + "/" + String.format("%03d", ++seq);
        }
        return id;
    }

    private ComplaintDto.ComplaintResponse toResponse(ComplaintEntity c) {
        return ComplaintDto.ComplaintResponse.builder()
                .complaintId(c.getComplaintId())   // keep this
                .customerId(String.valueOf(c.getCustomerId()))
                .customerName(c.getCustomerName())
                .category(c.getCategory())
                .severity(c.getSeverity())
                .description(c.getDescription())
                .status(c.getStatus())
                .filedOn(c.getFiledOn())
                .build();
    }
    }

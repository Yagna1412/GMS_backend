package com.gms.backend.serviceadvisor.complaint.Controller;

import com.gms.backend.serviceadvisor.complaint.DTO.ComplaintDto;
import com.gms.backend.serviceadvisor.complaint.Service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ComplaintController {

    private final ComplaintService complaintService;

    // POST /api/complaints
    @PostMapping
    public ResponseEntity<ComplaintDto.ComplaintResponse> register(
            @Valid @RequestBody ComplaintDto.RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(complaintService.registerComplaint(request));
    }

    // GET /api/complaints/dashboard
    @GetMapping("/dashboard")
    public ResponseEntity<ComplaintDto.DashboardResponse> dashboard() {
        return ResponseEntity.ok(complaintService.getDashboard());
    }

    // GET /api/complaints
    @GetMapping
    public ResponseEntity<List<ComplaintDto.ComplaintResponse>> getAll() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    // GET /api/complaints/COMP-MUM-2025-001
    @GetMapping("/{complaintId}")
    public ResponseEntity<ComplaintDto.ComplaintResponse> getById(
            @PathVariable String complaintId) {
        return ResponseEntity.ok(complaintService.getComplaintById(complaintId));
    }

    // PUT /api/complaints/COMP-MUM-2025-001/status
    @PutMapping("/{complaintId}/status")
    public ResponseEntity<ComplaintDto.ComplaintResponse> updateStatus(
            @PathVariable String complaintId,
            @Valid @RequestBody ComplaintDto.StatusUpdateRequest request) {
        return ResponseEntity.ok(complaintService.updateStatus(complaintId, request));
    }

    // DELETE /api/complaints/COMP-MUM-2025-001
    @DeleteMapping("/{complaintId}")
    public ResponseEntity<Void> delete(@PathVariable String complaintId) {
        complaintService.deleteComplaint(complaintId);
        return ResponseEntity.noContent().build();
    }
}
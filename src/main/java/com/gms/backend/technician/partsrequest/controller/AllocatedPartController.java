package com.gms.backend.technician.partsrequest.controller;

import com.gms.backend.technician.partsrequest.dto.ActiveJobAllocationsDto;
import com.gms.backend.technician.partsrequest.service.AllocatedPartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocated-parts")
@RequiredArgsConstructor
public class AllocatedPartController {

    private final AllocatedPartService service;

    // "Allocated Parts (Active Jobs)" section
    // Returns: [{ jobCardId: "JC/MUM/2024/0098", parts: [{partName, qty, status}] }]
    @GetMapping
    public ResponseEntity<List<ActiveJobAllocationsDto>> getAllGrouped() {
        return ResponseEntity.ok(service.getAllGroupedByJobCard());
    }
}
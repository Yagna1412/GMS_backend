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
@CrossOrigin(origins = "http://localhost:5173")
public class AllocatedPartController {

    private final AllocatedPartService service;

    // "Allocated Parts (Active Jobs)" section

    @GetMapping
    public ResponseEntity<List<ActiveJobAllocationsDto>> getAllGrouped() {
        return ResponseEntity.ok(service.getAllGroupedByJobCard());
    }
}
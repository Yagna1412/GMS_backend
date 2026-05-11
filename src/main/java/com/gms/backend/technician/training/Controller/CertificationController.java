package com.gms.backend.technician.training.Controller;

import com.gms.backend.technician.training.Dto.CertificationDTO;
import com.gms.backend.technician.training.Service.CertificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/certifications")
public class CertificationController {

    @Autowired
    private CertificationService service;

    // Get Certifications by User ID
    @GetMapping("/{userId}")
    public List<CertificationDTO> getCertifications(
            @PathVariable Long userId,
            @RequestParam(required = false) String status
    ) {
        return service.getCertifications(userId, status);
    }

    // Renew Certification
    @PostMapping("/{id}/renew")
    public Map<String, Object> renew(
            @PathVariable Long id,
            @RequestBody(required = false) Map<String, Integer> req
    ) {

        int years =
                req != null
                        ? req.getOrDefault("years", 2)
                        : 2;

        return service.renew(id, years);
    }
}
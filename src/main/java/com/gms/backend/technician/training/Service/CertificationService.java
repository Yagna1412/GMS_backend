package com.gms.backend.technician.training.Service;

import com.gms.backend.technician.training.Dto.CertificationDTO;
import com.gms.backend.technician.training.Entity.Certification;
import com.gms.backend.technician.training.Entity.UserCertification;
import com.gms.backend.technician.training.Repository.CertificationRepository;
import com.gms.backend.technician.training.Repository.UserCertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class CertificationService {

    @Autowired
    private UserCertificationRepository repo;

    @Autowired
    private CertificationRepository certRepo;

    public List<CertificationDTO> getCertifications(Long userId, String statusFilter) {

        LocalDate today = LocalDate.now();

        return repo.findByUserId(userId).stream().map(c -> {

            Certification cert = certRepo.findById(c.getCertificationId()).orElse(null);

            CertificationDTO dto = new CertificationDTO();

            dto.setUserCertificationId(c.getId());
            dto.setName(cert.getName());
            dto.setCode(cert.getCode());
            dto.setIssueDate(c.getIssueDate());
            dto.setExpiryDate(c.getExpiryDate());

            String status;

            if (!c.getExpiryDate().isAfter(today.plusDays(30))) {
                status = "EXPIRING_SOON";
                dto.setShowRenewButton(true);
            } else {
                status = "ACTIVE";
                dto.setShowRenewButton(false);
            }

            dto.setStatus(status);

            return dto;

        }).filter(dto ->
                statusFilter == null || dto.getStatus().equals(statusFilter)
        ).toList();
    }

    public Map<String, Object> renew(Long id, int years) {

        UserCertification cert = repo.findById(id).orElseThrow();

        cert.setExpiryDate(cert.getExpiryDate().plusYears(years));
        repo.save(cert);

        return Map.of(
                "message", "Renewed successfully",
                "newExpiryDate", cert.getExpiryDate(),
                "status", "ACTIVE"
        );
    }
}
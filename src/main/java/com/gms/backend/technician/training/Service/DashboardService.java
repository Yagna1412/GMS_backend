package com.gms.backend.technician.training.Service;

import com.gms.backend.technician.training.Dto.DashboardDTO;
import com.gms.backend.technician.training.Repository.UserCertificationRepository;
import com.gms.backend.technician.training.Repository.UserTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private UserTrainingRepository trainingRepo;

    @Autowired
    private UserCertificationRepository certRepo;

    public DashboardDTO getSummary(Long userId) {

        long active = trainingRepo.countByUserIdAndStatus(userId, "IN_PROGRESS");
        long completed = trainingRepo.countByUserIdAndStatus(userId, "COMPLETED");

        long certifications = certRepo.countByUserId(userId);

        long expiringSoon = certRepo.findByUserId(userId).stream()
                .filter(c -> c.getExpiryDate() != null &&
                        c.getExpiryDate().isBefore(java.time.LocalDate.now().plusDays(30)))
                .count();

        return new DashboardDTO(active, completed, certifications, expiringSoon);
    }
}
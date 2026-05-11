package com.gms.backend.technician.training.Repository;

import com.gms.backend.technician.training.Entity.UserCertification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCertificationRepository extends JpaRepository<UserCertification, Long> {

    // Get all certifications for a user
    List<UserCertification> findByUserId(Long userId);

    // Count total certifications for dashboard
    long countByUserId(Long userId);
}
package com.gms.backend.technician.training.Repository;

import com.gms.backend.technician.training.Entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
}
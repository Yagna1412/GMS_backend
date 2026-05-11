package com.gms.backend.technician.training.Repository;

import com.gms.backend.technician.training.Entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
package com.gms.backend.technician.training.Repository;

import com.gms.backend.technician.training.Entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findByTrainingId(Long trainingId);
}
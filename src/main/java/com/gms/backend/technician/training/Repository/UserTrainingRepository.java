package com.gms.backend.technician.training.Repository;

import com.gms.backend.technician.training.Entity.UserTraining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTrainingRepository extends JpaRepository<UserTraining, Long> {

    List<UserTraining> findByUserId(Long userId);

    long countByUserIdAndStatus(Long userId, String status);
}
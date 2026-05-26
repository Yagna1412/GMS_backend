package com.gms.backend.hr.dashboard.repository;

import com.gms.backend.hr.dashboard.entity.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, Long> {
    List<TrainingProgram> findByStatus(String status);
    List<TrainingProgram> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<TrainingProgram> findByTrainer(String trainer);
    List<TrainingProgram> findByLocation(String location);
}

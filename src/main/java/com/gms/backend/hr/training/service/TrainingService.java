package com.gms.backend.hr.training.service;

import com.gms.backend.hr.training.dto.TrainingProgramDTO;
import com.gms.backend.hr.training.entity.TrainingFeedback;
import com.gms.backend.hr.training.entity.TrainingMaterial;
import com.gms.backend.hr.training.entity.TrainingProgram;

import java.util.List;

public interface TrainingService {

    // TRAINING PROGRAM

    TrainingProgram createTraining(
            TrainingProgramDTO dto);

    List<TrainingProgram>
    getAllTrainings();

    TrainingProgram getTrainingById(
            Long id);

    TrainingProgram updateTraining(
            Long id,
            TrainingProgramDTO dto);

    void deleteTraining(
            Long id);

    // FEEDBACK

    TrainingFeedback createFeedback(
            TrainingFeedback feedback);

    List<TrainingFeedback>
    getAllFeedback();

    // MATERIALS

    TrainingMaterial createMaterial(
            TrainingMaterial material);

    List<TrainingMaterial>
    getAllMaterials();

    void sendTrainingNotifications();

    Object getTrainingCalendarData();

    Object getInvestmentReports();

    Object getAnalyticsDashboardData();

    List<TrainingMaterial> getMaterialsByProgramId(Long id);

    List<TrainingFeedback> getFeedbackByProgramId(Long id);

    List<TrainingProgram> getProgramsByType(String type);

    List<TrainingProgram> getProgramsByStatus(String status);
}
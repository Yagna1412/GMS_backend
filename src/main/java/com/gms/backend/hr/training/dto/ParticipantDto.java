package com.gms.backend.hr.training.dto;

import lombok.Data;

@Data
public class ParticipantDto {
    private Long id;
    private Long trainingProgramId;

    private Long employeeId;

    private String attendanceStatus;

    private String completionStatus;

    private Integer feedbackRating;

    private Long staffId;

    private Double attendancePercentage;

    private Long trainingId;
}
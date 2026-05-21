package com.gms.backend.hr.training.dto;


import lombok.Data;

@Data
public class TrainingProgramDTO {

    private String programName;
    private String description;
    private String trainingType;
    private Integer durationDays;
    private Double cost;
    private String status;
    private Long trainerId;

    public Long getTrainerId() {
        return this.trainerId;
    }
}
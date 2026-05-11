package com.gms.backend.technician.training.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TrainingDTO {

    private Long trainingId;
    private String title;
    private String code;
    private String status;

    private Integer progress;
    private LocalDate dueDate;

    private LocalDate completionDate;
    private String certificateCode;

    private Boolean materialsAvailable;
}
package com.gms.backend.hr.training.dto;


import lombok.Data;

@Data
public class TrainerDTO {

    private String trainerName;
    private String trainerType;
    private String organization;
    private String email;
    private String phone;
    private String specialization;
}
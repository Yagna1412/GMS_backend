package com.gms.backend.technician.training.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CertificationDTO {

    private Long userCertificationId;
    private String name;
    private String code;

    private LocalDate issueDate;
    private LocalDate expiryDate;

    private String status;
    private Boolean showRenewButton;
}
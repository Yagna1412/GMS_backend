package com.gms.backend.hr.performance.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppraisalCycleDto {

    private Long cycleId;

    private String cycleName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;
}
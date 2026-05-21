package com.gms.backend.inventory.reports.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailReportRequestDto {

    private String email;
    private String reportType;
}

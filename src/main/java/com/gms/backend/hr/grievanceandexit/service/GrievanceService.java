package com.gms.backend.hr.grievanceandexit.service;

import com.gms.backend.hr.grievanceandexit.dto.*;
import com.gms.backend.hr.grievanceandexit.entity.EmployeeExit;
import com.gms.backend.hr.grievanceandexit.entity.Grievance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface GrievanceService {

    String createGrievance(GrievanceRequestDto dto);

    List<Grievance> getAllGrievances();

    Map<String, Long> getDashboardSummary();

    String updateStatus(Long id, StatusUpdateDto dto);

    List<Grievance> searchByStatus(String status);

    String createExitRequest(ExitRequestDto dto);

    List<EmployeeExit> getAllExitRequests();

    String conductInterview(Long id, ExitInterviewDto dto);

    BigDecimal calculateSettlement(Long id, SettlementDto dto);

    String generateLetter(Long id);
}

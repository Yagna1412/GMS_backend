package com.gms.backend.hr.grievanceandexit.service;


import com.gms.backend.hr.grievanceandexit.dto.*;
import com.gms.backend.hr.grievanceandexit.entity.EmployeeExit;
import com.gms.backend.hr.grievanceandexit.entity.ExitInterview;
import com.gms.backend.hr.grievanceandexit.entity.FinalSettlement;
import com.gms.backend.hr.grievanceandexit.entity.Grievance;
import com.gms.backend.hr.grievanceandexit.repository.EmployeeExitRepository;
import com.gms.backend.hr.grievanceandexit.repository.ExitInterviewRepository;
import com.gms.backend.hr.grievanceandexit.repository.FinalSettlementRepository;
import com.gms.backend.hr.grievanceandexit.repository.GrievanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class GrievanceServiceImpl implements GrievanceService {



        private final GrievanceRepository grievanceRepository;
        private final EmployeeExitRepository employeeExitRepository;
        private final ExitInterviewRepository exitInterviewRepository;
        private final FinalSettlementRepository finalSettlementRepository;

        // 1. Create Grievance
        @Override
        public String createGrievance(GrievanceRequestDto dto) {

            Grievance grievance = Grievance.builder()
                    .grievanceCode("GRV/2026/" + System.currentTimeMillis())
                    .employeeId(dto.getEmployeeId())
                    .employeeName(dto.getEmployeeName())
                    .category(dto.getCategory())
                    .severity(dto.getSeverity())
                    .description(dto.getDescription())
                    .status("PENDING")
                    .slaDays(7)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            grievanceRepository.save(grievance);

            return "Grievance created successfully";
        }

        // 2. Get All Grievances
        @Override
        public List<Grievance> getAllGrievances() {

            return grievanceRepository.findAll();
        }

        // 3. Dashboard Summary
        @Override
        public Map<String, Long> getDashboardSummary() {

            Map<String, Long> dashboard = new HashMap<>();

            dashboard.put("total", grievanceRepository.count());
            dashboard.put("pending", grievanceRepository.countByStatus("PENDING"));
            dashboard.put("resolved", grievanceRepository.countByStatus("RESOLVED"));
            dashboard.put("highSeverity", grievanceRepository.countBySeverity("HIGH"));

            return dashboard;
        }

        // 4. Update Grievance Status
        @Override
        public String updateStatus(Long id, StatusUpdateDto dto) {

            Grievance grievance = grievanceRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Grievance not found"));

            grievance.setStatus(dto.getStatus());
            grievance.setRemarks(dto.getRemarks());
            grievance.setUpdatedAt(LocalDateTime.now());

            grievanceRepository.save(grievance);

            return "Status updated successfully";
        }

        // 5. Search By Status
        @Override
        public List<Grievance> searchByStatus(String status) {

            return grievanceRepository.findByStatus(status);
        }

        // 6. Create Exit Request
        @Override
        public String createExitRequest(ExitRequestDto dto) {

            EmployeeExit employeeExit = EmployeeExit.builder()
                    .employeeId(dto.getEmployeeId())
                    .employeeName(dto.getEmployeeName())
                    .reason(dto.getReason())
                    .lastWorkingDate(dto.getLastWorkingDate())
                    .exitStatus("IN_PROGRESS")
                    .createdAt(LocalDateTime.now())
                    .build();

            employeeExitRepository.save(employeeExit);

            return "Exit request created successfully";
        }

        // 7. Get All Exit Requests
        @Override
        public List<EmployeeExit> getAllExitRequests() {

            return employeeExitRepository.findAll();
        }

        // 8. Conduct Exit Interview
        @Override
        public String conductInterview(Long id, ExitInterviewDto dto) {

            ExitInterview interview = ExitInterview.builder()
                    .exitId(id)
                    .feedback(dto.getFeedback())
                    .rehireEligible(dto.getRehireEligible())
                    .build();

            exitInterviewRepository.save(interview);

            return "Exit interview submitted successfully";
        }

        // 9. Calculate Final Settlement
        @Override
        public BigDecimal calculateSettlement(Long id, SettlementDto dto) {

            BigDecimal finalAmount = dto.getSalary()
                    .add(dto.getBonus())
                    .subtract(dto.getDeductions());

            FinalSettlement settlement = FinalSettlement.builder()
                    .exitId(id)
                    .salary(dto.getSalary())
                    .bonus(dto.getBonus())
                    .deductions(dto.getDeductions())
                    .finalAmount(finalAmount)
                    .build();

            finalSettlementRepository.save(settlement);

            return finalAmount;
        }

        // 10. Generate Letter
        @Override
        public String generateLetter(Long id) {

            return "Relieving letter generated successfully";
        }
    }


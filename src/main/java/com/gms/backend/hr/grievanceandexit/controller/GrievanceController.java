package com.gms.backend.hr.grievanceandexit.controller;


import com.gms.backend.hr.grievanceandexit.dto.*;
import com.gms.backend.hr.grievanceandexit.entity.EmployeeExit;
import com.gms.backend.hr.grievanceandexit.entity.Grievance;
import com.gms.backend.hr.grievanceandexit.service.GrievanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GrievanceController {


    private final GrievanceService grievanceService;

    // 1. Create Grievance
    @PostMapping("/grievances")
    public String createGrievance(@RequestBody GrievanceRequestDto dto) {

        return grievanceService.createGrievance(dto);
    }

    // 2. Get All Grievances
    @GetMapping("/grievances")
    public List<Grievance> getAllGrievances() {

        return grievanceService.getAllGrievances();
    }

    // 3. Dashboard Summary
    @GetMapping("/grievances/dashboard")
    public Map<String, Long> getDashboardSummary() {

        return grievanceService.getDashboardSummary();
    }

    // 4. Update Grievance Status
    @PutMapping("/grievances/{id}/status")
    public String updateStatus(@PathVariable Long id,
                               @RequestBody StatusUpdateDto dto) {

        return grievanceService.updateStatus(id, dto);
    }

    // 5. Search / Filter Grievances
    @GetMapping("/grievances/search")
    public List<Grievance> searchByStatus(@RequestParam String status) {

        return grievanceService.searchByStatus(status);
    }

    // 6. Create Exit Request
    @PostMapping("/exits")
    public String createExitRequest(@RequestBody ExitRequestDto dto) {

        return grievanceService.createExitRequest(dto);
    }

    // 7. Get All Exit Requests
    @GetMapping("/exits")
    public List<EmployeeExit> getAllExitRequests() {

        return grievanceService.getAllExitRequests();
    }

    // 8. Conduct Exit Interview
    @PostMapping("/exits/{id}/interview")
    public String conductInterview(@PathVariable Long id,
                                   @RequestBody ExitInterviewDto dto) {

        return grievanceService.conductInterview(id, dto);
    }

    // 9. Calculate Final Settlement
    @PostMapping("/exits/{id}/settlement")
    public BigDecimal calculateSettlement(@PathVariable Long id,
                                          @RequestBody SettlementDto dto) {

        return grievanceService.calculateSettlement(id, dto);
    }

    // 10. Generate Relieving Letter
    @PostMapping("/exits/{id}/generate-letter")
    public String generateLetter(@PathVariable Long id) {

        return grievanceService.generateLetter(id);
    }
}


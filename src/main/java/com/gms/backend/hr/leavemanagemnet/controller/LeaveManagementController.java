package com.gms.backend.hr.leavemanagemnet.controller;

import com.gms.backend.hr.leavemanagemnet.dto.LeaveResponseDto;
import com.gms.backend.hr.leavemanagemnet.service.LeaveManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin("*")
public class LeaveManagementController {

    private final LeaveManagementService service;

    public LeaveManagementController(
            LeaveManagementService service
    ) {
        this.service = service;
    }

    // Get All Leaves
    @GetMapping
    public List<LeaveResponseDto> getLeaves(

            @RequestParam(
                    value = "status",
                    required = false
            )
            String status
    ) {

        return service.getLeaves(status);
    }

    // Dashboard Stats
    @GetMapping("/stats")
    public Map<String, Long> getStats() {

        return service.getStats();
    }

    // Approve Leave
    @PutMapping("/{id}/approve")
    public String approveLeave(

            @PathVariable(value = "id")
            Long id
    ) {

        return service.approveLeave(id);
    }

    // Reject Leave
    @PutMapping("/{id}/reject")
    public String rejectLeave(

            @PathVariable(value = "id")
            Long id
    ) {

        return service.rejectLeave(id);
    }
}
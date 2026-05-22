package com.gms.backend.hr.employeemaster.controller;

import com.gms.backend.hr.employeemaster.dto.EmployeeMasterRequestDto;
import com.gms.backend.hr.employeemaster.dto.EmployeeMasterResponseDto;
import com.gms.backend.hr.employeemaster.service.EmployeeMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeMasterController {

    @Autowired
    private EmployeeMasterService employeeMasterService;

    @PostMapping
    public String createEmployee(
            @RequestBody EmployeeMasterRequestDto employeeMasterRequestDto) {

        return employeeMasterService.createEmployee(employeeMasterRequestDto);
    }

    @GetMapping
    public List<EmployeeMasterResponseDto> getAllEmployees() {

        return employeeMasterService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeMasterResponseDto getEmployeeById(
            @PathVariable Long id) {

        return employeeMasterService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public String updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeMasterRequestDto employeeMasterRequestDto) {

        return employeeMasterService.updateEmployee(id,
                employeeMasterRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        return employeeMasterService.deleteEmployee(id);
    }
}

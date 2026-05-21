package com.gms.backend.hr.controller;

import com.gms.backend.hr.dto.PayrollDTO;
import com.gms.backend.hr.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hr/payroll")
@CrossOrigin("*")
public class PayrollController {
    
    @Autowired
    private PayrollService payrollService;
    
    @PostMapping
    public ResponseEntity<?> createPayroll(@RequestBody PayrollDTO payrollDTO) {
        try {
            PayrollDTO created = payrollService.createPayroll(payrollDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getPayroll(@PathVariable Long id) {
        PayrollDTO payroll = payrollService.getPayrollById(id);
        if (payroll != null) {
            return new ResponseEntity<>(payroll, HttpStatus.OK);
        }
        return new ResponseEntity<>("Payroll record not found", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEmployeePayroll(@PathVariable Long employeeId) {
        List<PayrollDTO> payrolls = payrollService.getEmployeePayroll(employeeId);
        return new ResponseEntity<>(payrolls, HttpStatus.OK);
    }
    
    @GetMapping("/pending")
    public ResponseEntity<?> getPendingPayrolls() {
        List<PayrollDTO> payrolls = payrollService.getPendingPayrolls();
        return new ResponseEntity<>(payrolls, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/process")
    public ResponseEntity<?> processPayroll(@PathVariable Long id) {
        try {
            PayrollDTO processed = payrollService.processPayroll(id);
            if (processed != null) {
                return new ResponseEntity<>(processed, HttpStatus.OK);
            }
            return new ResponseEntity<>("Payroll record not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayroll(@PathVariable Long id) {
        try {
            payrollService.deletePayroll(id);
            return new ResponseEntity<>("Payroll record deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/total/{month}")
    public ResponseEntity<?> getTotalPayrollForMonth(@PathVariable String month) {
        try {
            LocalDate date = LocalDate.parse(month + "-01");
            var total = payrollService.getTotalPayrollForMonth(date);
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

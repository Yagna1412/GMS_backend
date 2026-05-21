package com.gms.backend.hr.payroll.Controller;

import com.gms.backend.hr.payroll.Dto.PayrollDto;
import com.gms.backend.hr.payroll.Dto.PayrollSummaryDto;
import com.gms.backend.hr.payroll.Entity.Payroll;
import com.gms.backend.hr.payroll.Service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@CrossOrigin("*")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    // PROCESS SINGLE EMPLOYEE
    @PostMapping("/process")
    public Payroll processPayroll(@RequestBody PayrollDto dto) {
        return payrollService.processPayroll(
                dto.getEmployeeId(),
                dto.getMonth()
        );
    }

    // PROCESS ENTIRE MONTH
    @PostMapping("/process-month")
    public String processMonthlyPayroll(
            @RequestParam String month) {

        payrollService.processMonthlyPayroll(month);

        return "Payroll Processed Successfully";
    }

    // GET ALL
    @GetMapping
    public List<Payroll> getAllPayrolls() {
        return payrollService.getAllPayrolls();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Payroll getPayrollById(@PathVariable Long id) {
        return payrollService.getPayrollById(id);
    }

    // MONTH FILTER
    @GetMapping("/month/{month}")
    public List<Payroll> getPayrollByMonth(
            @PathVariable String month) {

        return payrollService.getPayrollByMonth(month);
    }

    // SUMMARY
    @GetMapping("/summary")
    public PayrollSummaryDto getSummary() {
        return payrollService.getSummary();
    }

    // UPDATE
    @PutMapping("/{id}")
    public Payroll updatePayroll(@PathVariable Long id,
                                 @RequestBody Payroll payroll) {

        return payrollService.updatePayroll(id, payroll);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deletePayroll(@PathVariable Long id) {

        payrollService.deletePayroll(id);

        return "Payroll Deleted Successfully";
    }

    // GENERATE SLIPS
    @PostMapping("/generate-slips/{month}")
    public String generateSlips(
            @PathVariable String month) {

        return payrollService.generateSalarySlips(month);
    }

    // DOWNLOAD SLIP
    @GetMapping("/slip/{id}")
    public String downloadSlip(@PathVariable Long id) {

        return payrollService.downloadSlip(id);
    }
}
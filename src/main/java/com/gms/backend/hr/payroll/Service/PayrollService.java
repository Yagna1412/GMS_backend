package com.gms.backend.hr.payroll.Service;

import com.gms.backend.hr.payroll.Dto.PayrollSummaryDto;
import com.gms.backend.hr.payroll.Entity.Payroll;
import com.gms.backend.hr.payroll.Repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    // PROCESS SINGLE PAYROLL
    public Payroll processPayroll(Long employeeId, String month) {

        Payroll payroll = new Payroll();

        payroll.setEmployeeId(employeeId);

        payroll.setStaffId(employeeId);

        payroll.setMonth(month);

        payroll.setYear(2024);

        payroll.setStatus("Completed");

        // Dummy Salary
        double basicSalary = 50000;

        double pf = basicSalary * 0.12;
        double esi = basicSalary * 0.0175;
        double tds = basicSalary * 0.10;

        double netSalary = basicSalary - pf - esi - tds;

        payroll.setBasicSalary(basicSalary);
        payroll.setPf(pf);
        payroll.setEsi(esi);
        payroll.setTds(tds);
        payroll.setNetSalary(netSalary);

        return payrollRepository.save(payroll);
    }

    // GET ALL PAYROLLS
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    // GET PAYROLL BY ID
    public Payroll getPayrollById(Long id) {

        return payrollRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Payroll Not Found"));
    }

    // UPDATE PAYROLL
    public Payroll updatePayroll(Long id,
                                 Payroll updatedPayroll) {

        Payroll payroll = getPayrollById(id);

        payroll.setMonth(updatedPayroll.getMonth());
        payroll.setYear(updatedPayroll.getYear());
        payroll.setStatus(updatedPayroll.getStatus());
        payroll.setBasicSalary(updatedPayroll.getBasicSalary());
        payroll.setPf(updatedPayroll.getPf());
        payroll.setEsi(updatedPayroll.getEsi());
        payroll.setTds(updatedPayroll.getTds());
        payroll.setNetSalary(updatedPayroll.getNetSalary());

        return payrollRepository.save(payroll);
    }

    // DELETE PAYROLL
    public void deletePayroll(Long id) {
        payrollRepository.deleteById(id);
    }

    // SUMMARY
    public PayrollSummaryDto getSummary() {

        List<Payroll> payrolls = payrollRepository.findAll();

        long totalEmployees = payrolls.size();

        double totalSalary = payrolls.stream()
                .mapToDouble(Payroll::getNetSalary)
                .sum();

        String status = payrolls.stream()
                .anyMatch(p ->
                        "Pending".equalsIgnoreCase(p.getStatus()))
                ? "Pending"
                : "Completed";

        return new PayrollSummaryDto(
                totalEmployees,
                totalSalary,
                status
        );
    }

    // FILTER BY MONTH
    public List<Payroll> getPayrollByMonth(String month) {
        return payrollRepository.findByMonth(month);
    }

    // PROCESS FULL MONTH
    public void processMonthlyPayroll(String month) {

        for (long i = 1; i <= 5; i++) {
            processPayroll(i, month);
        }
    }

    // DOWNLOAD SLIP
    public String downloadSlip(Long id) {

        Payroll payroll = getPayrollById(id);

        return "Salary Slip Generated Successfully For Payroll ID : "
                + payroll.getId();
    }

    // GENERATE ALL SLIPS
    public String generateSalarySlips(String month) {

        List<Payroll> payrolls =
                payrollRepository.findByMonth(month);

        return payrolls.size()
                + " Salary Slips Generated";
    }
}
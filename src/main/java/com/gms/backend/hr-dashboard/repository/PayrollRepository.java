package com.gms.backend.hr.repository;

import com.gms.backend.hr.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByEmployeeId(Long employeeId);
    List<Payroll> findByStatus(String status);
    List<Payroll> findByPayrollMonth(LocalDate payrollMonth);
    List<Payroll> findByEmployeeIdAndPayrollMonth(Long employeeId, LocalDate payrollMonth);
}

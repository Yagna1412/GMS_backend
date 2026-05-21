package com.gms.backend.hr.payroll.Repository;

import com.gms.backend.hr.payroll.Entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    List<Payroll> findByMonth(String month);
}
package com.gms.backend.hr.attendance.repository;


import com.gms.backend.hr.attendance.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}


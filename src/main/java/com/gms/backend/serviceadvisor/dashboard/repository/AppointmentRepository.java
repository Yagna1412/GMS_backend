package com.gms.backend.serviceadvisor.dashboard.repository;

import com.gms.backend.serviceadvisor.dashboard.entity.Appointment;
import com.gms.backend.serviceadvisor.dashboard.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    long countByAppointmentDate(LocalDate appointmentDate);

    long countByAppointmentDateAndStatusIn(LocalDate appointmentDate, List<AppointmentStatus> statuses);

    List<Appointment> findByAppointmentDateOrderByAppointmentTimeAsc(LocalDate appointmentDate);
}
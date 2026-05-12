package com.gms.backend.serviceadvisor.appointment.repository;

import com.gms.backend.serviceadvisor.appointment.model.Appointment;
import com.gms.backend.serviceadvisor.appointment.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByAppointmentDate(LocalDate date);

    long countByAppointmentDate(LocalDate date);

    long countByStatusAndAppointmentDate(AppointmentStatus status, LocalDate date);

    @Query("SELECT a FROM Appointment a WHERE " +
            "LOWER(a.notes) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(a.customer.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(a.customer.lastName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "CAST(a.id AS string) LIKE CONCAT('%', :query, '%')")
    List<Appointment> searchAppointments(@Param("query") String query);
}
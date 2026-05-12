package com.gms.backend.serviceadvisor.appointment.service;

import com.gms.backend.serviceadvisor.appointment.model.Appointment;
import com.gms.backend.serviceadvisor.appointment.model.AppointmentStatus;
import com.gms.backend.serviceadvisor.appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public Map<String, Long> getDashboardStats() {
        LocalDate today = LocalDate.now();
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalToday", repository.countByAppointmentDate(today));
        stats.put("scheduled", repository.countByStatusAndAppointmentDate(AppointmentStatus.Scheduled, today));
        stats.put("inProgress", repository.countByStatusAndAppointmentDate(AppointmentStatus.InProgress, today));
        stats.put("completed", repository.countByStatusAndAppointmentDate(AppointmentStatus.Completed, today));
        return stats;
    }

    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return repository.findByAppointmentDate(date);
    }

    public Appointment save(Appointment appointment) {
        return repository.save(appointment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Appointment> search(String query) {
        return repository.searchAppointments(query);
    }

    @Transactional(readOnly = true)
    public Appointment findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found with id: " + id));
    }
}
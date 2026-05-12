package com.gms.backend.serviceadvisor.appointment.controller;

import com.gms.backend.serviceadvisor.appointment.model.Appointment;
import com.gms.backend.serviceadvisor.appointment.model.AppointmentStatus;
import com.gms.backend.serviceadvisor.appointment.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Long>> getDashboard() {
        return ResponseEntity.ok(service.getDashboardStats());
    }

    @GetMapping
    public List<Appointment> getByDate(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate targetDate = (date != null) ? date : LocalDate.now();
        return service.getAppointmentsByDate(targetDate);
    }

    @PostMapping
    public Appointment create(@Valid @RequestBody Appointment appointment) {
        return service.save(appointment);
    }

    @PutMapping("/{id}/status")
    public Appointment updateStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        Appointment appt = service.findById(id);
        String statusValue = statusUpdate.get("status");
        try {
            appt.setStatus(AppointmentStatus.valueOf(statusValue));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status value: " + statusValue);
        }
        return service.save(appt);
    }

    @PutMapping("/{id}")
    public Appointment update(@PathVariable Long id, @RequestBody Appointment appointment) {
        Appointment existing = service.findById(id);

        // Preserve relationships if not provided in request
        if (appointment.getCustomer() == null) {
            appointment.setCustomer(existing.getCustomer());
        }
        if (appointment.getVehicle() == null) {
            appointment.setVehicle(existing.getVehicle());
        }
        if (appointment.getTechnician() == null) {
            appointment.setTechnician(existing.getTechnician());
        }

        appointment.setId(existing.getId());
        appointment.setCreatedAt(existing.getCreatedAt());
        appointment.setStatus(existing.getStatus()); // Preserve status unless explicitly changed

        return service.save(appointment);
    }

    @GetMapping("/search")
    public List<Appointment> search(@RequestParam String query) {
        return service.search(query);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
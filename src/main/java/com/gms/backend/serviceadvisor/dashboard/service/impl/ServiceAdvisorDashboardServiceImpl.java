package com.gms.backend.serviceadvisor.dashboard.service.impl;

import com.gms.backend.serviceadvisor.dashboard.dto.*;
import com.gms.backend.serviceadvisor.dashboard.entity.Appointment;
import com.gms.backend.serviceadvisor.dashboard.entity.Complaint;
import com.gms.backend.serviceadvisor.dashboard.entity.Estimation;
import com.gms.backend.serviceadvisor.dashboard.enums.AppointmentStatus;
import com.gms.backend.serviceadvisor.dashboard.enums.ComplaintStatus;
import com.gms.backend.serviceadvisor.dashboard.enums.EstimationStatus;
import com.gms.backend.serviceadvisor.dashboard.enums.JobCardStatus;
import com.gms.backend.serviceadvisor.dashboard.repository.AppointmentRepository;
import com.gms.backend.serviceadvisor.dashboard.repository.ComplaintRepository;
import com.gms.backend.serviceadvisor.dashboard.repository.EstimationRepository;
import com.gms.backend.serviceadvisor.dashboard.repository.JobCardRepository;
import com.gms.backend.serviceadvisor.dashboard.service.ServiceAdvisorDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceAdvisorDashboardServiceImpl implements ServiceAdvisorDashboardService {

    private final AppointmentRepository appointmentRepository;
    private final EstimationRepository estimationRepository;
    private final JobCardRepository jobCardRepository;
    private final ComplaintRepository complaintRepository;

    @Override
    public DashboardKpiResponseDto getKpis() {
        LocalDate today = LocalDate.now();

        long totalAppointments = appointmentRepository.countByAppointmentDate(today);
        long activeAppointments = appointmentRepository.countByAppointmentDateAndStatusIn(
                today,
                List.of(AppointmentStatus.SCHEDULED, AppointmentStatus.IN_PROGRESS)
        );

        long pendingEstimations = estimationRepository.countByStatus(EstimationStatus.PENDING);
        long jobsInProgress = jobCardRepository.countByStatus(JobCardStatus.IN_PROGRESS);
        long pendingDeliveries = jobCardRepository.countByStatus(JobCardStatus.READY_TO_DELIVER);

        return new DashboardKpiResponseDto(
                new AppointmentKpiDto(totalAppointments, activeAppointments),
                new EstimationKpiDto(pendingEstimations, "Awaiting Approval"),
                new JobKpiDto(jobsInProgress, "Active Work"),
                new DeliveryKpiDto(pendingDeliveries, "Ready to Deliver")
        );
    }

    @Override
    public TodayScheduleResponseDto getTodaySchedule() {
        LocalDate today = LocalDate.now();

        List<Appointment> appointments = appointmentRepository
                .findByAppointmentDateOrderByAppointmentTimeAsc(today);

        List<AppointmentScheduleDto> appointmentDtos = appointments.stream()
                .map(appointment -> new AppointmentScheduleDto(
                        appointment.getId(),
                        appointment.getAppointmentTime().format(DateTimeFormatter.ofPattern("hh:mm a")),
                        appointment.getCustomer().getCustomerName(),
                        appointment.getVehicleName(),
                        formatEnum(appointment.getStatus().name())
                ))
                .toList();

        return new TodayScheduleResponseDto(today, appointmentDtos);
    }

    @Override
    public PendingEstimationsResponseDto getPendingEstimations() {
        List<Estimation> estimations = estimationRepository
                .findByStatusOrderByCreatedDateDesc(EstimationStatus.PENDING);

        List<PendingEstimationDto> data = estimations.stream()
                .map(estimation -> new PendingEstimationDto(
                        estimation.getId(),
                        estimation.getEstimationNumber(),
                        estimation.getCustomer().getCustomerName(),
                        estimation.getAmount(),
                        estimation.getStatus().name()
                ))
                .toList();

        return new PendingEstimationsResponseDto(data.size(), data);
    }

    @Override
    public ActiveComplaintsResponseDto getActiveComplaints() {
        List<ComplaintStatus> activeStatuses = List.of(
                ComplaintStatus.OPEN,
                ComplaintStatus.UNDER_REVIEW
        );

        List<Complaint> complaints = complaintRepository.findByStatusInOrderByIdDesc(activeStatuses);

        List<ActiveComplaintDto> data = complaints.stream()
                .map(complaint -> new ActiveComplaintDto(
                        complaint.getId(),
                        complaint.getCustomer().getCustomerName(),
                        complaint.getComplaintType(),
                        complaint.getPriority().name(),
                        complaint.getStatus().name()
                ))
                .toList();

        return new ActiveComplaintsResponseDto(data.size(), data);
    }

    @Override
    public DashboardStatusResponseDto getDashboardStatus() {
        LocalDate now = LocalDate.now();
        LocalDateTime monthStart = now.withDayOfMonth(1).atStartOfDay();
        LocalDateTime nextMonthStart = now.plusMonths(1).withDayOfMonth(1).atStartOfDay();

        long totalJobCards = jobCardRepository.countByCreatedOnBetween(monthStart, nextMonthStart);
        BigDecimal estimationValue = estimationRepository.sumAmountByStatus(EstimationStatus.PENDING);

        long completedJobs = jobCardRepository.countByStatus(JobCardStatus.COMPLETED);
        long totalJobs = jobCardRepository.count();

        int completionRate = totalJobs == 0 ? 0 : (int) ((completedJobs * 100) / totalJobs);

        return new DashboardStatusResponseDto(totalJobCards, estimationValue, completionRate);
    }

    private String formatEnum(String value) {
        String[] parts = value.toLowerCase().split("_");
        StringBuilder formatted = new StringBuilder();

        for (String part : parts) {
            if (!part.isEmpty()) {
                formatted.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1))
                        .append(" ");
            }
        }

        return formatted.toString().trim();
    }
}
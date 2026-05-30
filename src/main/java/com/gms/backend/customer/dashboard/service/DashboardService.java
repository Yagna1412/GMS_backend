package com.gms.backend.customer.dashboard.service;

import com.gms.backend.customer.dashboard.dto.*;
import com.gms.backend.customer.dashboard.entity.DashboardCustomer;
import com.gms.backend.customer.dashboard.entity.DashboardJobCard;
import com.gms.backend.customer.dashboard.repository.*;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final DashboardJobCardRepository jobRepo;
    private final DashboardBookingRepository bookingRepo;
    private final DashboardInvoiceRepository invoiceRepo;
    private final DashboardVehicleRepository vehicleRepo;

    public DashboardService(DashboardJobCardRepository jobRepo,
                            DashboardBookingRepository bookingRepo,
                            DashboardInvoiceRepository invoiceRepo,
                            DashboardVehicleRepository vehicleRepo) {
        this.jobRepo = jobRepo;
        this.bookingRepo = bookingRepo;
        this.invoiceRepo = invoiceRepo;
        this.vehicleRepo = vehicleRepo;
    }

    // 1. DASHBOARD SUMMARY
    public DashboardSummaryDTO getSummary(DashboardCustomer c) {
        if (c == null) throw new RuntimeException("User not authenticated");

        int upcoming = 0;
        int active = 0;
        double totalSpent = 0.0;

        try {
            upcoming = bookingRepo.countByCustomerAndStatus(c, "Booked");
        } catch (Exception e) {
            upcoming = 0;
        }

        try {
            active = jobRepo.countByCustomerAndStatus(c, "In Progress");
        } catch (Exception e) {
            active = 0;
        }

        try {
            Double value = invoiceRepo.sumTotalAmountByCustomer(c);
            if (value != null) totalSpent = value;
        } catch (Exception e) {
            totalSpent = 0.0;
        }

        int loyaltyPoints = (c.getLoyaltyPoints() != null) ? c.getLoyaltyPoints() : 0;

        return new DashboardSummaryDTO(upcoming, active, loyaltyPoints, totalSpent);
    }

    // 2. SPENDING HISTORY
    public List<SpendingHistoryDTO> getHistory(DashboardCustomer c) {
        if (c == null) throw new RuntimeException("User not authenticated");

        List<Object[]> data = invoiceRepo.getMonthlySpending(c);

        return data.stream()
                .map(obj -> {
                    int monthNumber = ((Number) obj[0]).intValue();
                    int amount = ((Number) obj[1]).intValue();
                    String monthName = Month.of(monthNumber).name().substring(0, 3);
                    return new SpendingHistoryDTO(monthName, amount);
                })
                .collect(Collectors.toList());
    }

    // 3. SERVICE BREAKDOWN
    public List<ServiceBreakdownDTO> getBreakdown(DashboardCustomer c) {
        if (c == null) throw new RuntimeException("User not authenticated");

        List<Object[]> data = jobRepo.getServiceBreakdown(c);

        return data.stream()
                .map(obj -> new ServiceBreakdownDTO(
                        obj[0] != null ? obj[0].toString() : "Unknown",
                        ((Number) obj[1]).intValue()
                ))
                .collect(Collectors.toList());
    }

    // 5. RECENT SERVICES
    public List<RecentServiceDTO> getRecentServices(DashboardCustomer c) {

        if (c == null)
            throw new RuntimeException("User not authenticated");

        List<DashboardJobCard> jobs =
                jobRepo.findTop5ByCustomerOrderByIdDesc(c);

        return jobs.stream()
                .map(job -> new RecentServiceDTO(
                        job.getDate() != null
                                ? job.getDate().toString()
                                : "N/A",

                        job.getServiceType() != null
                                ? job.getServiceType()
                                : "N/A",

                        job.getAmount(),

                        job.getStatus() != null
                                ? job.getStatus()
                                : "N/A"
                ))
                .toList();
    }

    // 6. VEHICLE - Return proper DTO with "number" field
    public VehicleDTO getVehicle(DashboardCustomer c) {
        if (c == null) return new VehicleDTO("No Vehicle", "N/A");

        return vehicleRepo.findByCustomer_Id(c.getId())
                .map(v -> new VehicleDTO(
                        v.getModel() != null ? v.getModel() : "No Vehicle",
                        v.getPlateNo() != null ? v.getPlateNo() : "N/A"
                ))
                .orElse(new VehicleDTO("No Vehicle", "N/A"));
    }

    // 7. PROFILE
    public ProfileDTO getProfile(DashboardCustomer c) {
        if (c == null) return new ProfileDTO("Alex", "alex@example.com");

        return new ProfileDTO(
                c.getName() != null ? c.getName() : "Alex",
                c.getEmail() != null ? c.getEmail() : "alex@example.com"
        );
    }
}
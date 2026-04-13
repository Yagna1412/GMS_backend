package com.gms.backend.dashboard.customerdashboardservice;

import com.gms.backend.customer.Customer;
import com.gms.backend.dashboard.customerdashboarddto.*;
import com.gms.backend.finance.Repository.InvoiceRepository;
import com.gms.backend.jobcard.JobCard;
import com.gms.backend.jobcard.Repository.JobCardRepository;
import com.gms.backend.jobcard.Repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final JobCardRepository jobRepo;
    private final BookingRepository bookingRepo;
    private final InvoiceRepository invoiceRepo;

    public DashboardService(JobCardRepository j, BookingRepository b, InvoiceRepository i) {
        this.jobRepo = j;
        this.bookingRepo = b;
        this.invoiceRepo = i;
    }

    // ✅ 1. SUMMARY
    public DashboardSummaryDTO getSummary(Customer c) {

        if (c == null) {
            throw new RuntimeException("User not authenticated");
        }

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
            if (value != null) {
                totalSpent = value;
            }
        } catch (Exception e) {
            totalSpent = 0.0;
        }

        int loyaltyPoints = (c.getLoyaltyPoints() != null) ? c.getLoyaltyPoints() : 0;

        return new DashboardSummaryDTO(
                upcoming,
                active,
                loyaltyPoints,
                totalSpent
        );
    }

    // ✅ 2. RECENT SERVICES (Table)
    public List<RecentServiceDTO> getRecentServices(Customer c) {

        if (c == null) {
            throw new RuntimeException("User not authenticated");
        }

        List<JobCard> jobs = jobRepo.findTop5ByCustomerOrderByIdDesc(c);

        return jobs.stream()
                .map(job -> new RecentServiceDTO(
                        job.getDate() != null ? job.getDate().toString() : "N/A",
                        job.getServiceType() != null ? job.getServiceType() : "N/A",
                        job.getBranch() != null ? job.getBranch() : "N/A",
                        job.getAmount(),
                        job.getStatus() != null ? job.getStatus() : "N/A"
                ))
                .collect(Collectors.toList());
    }

    // ✅ 3. SPENDING HISTORY (Graph)
    public List<SpendingHistoryDTO> getHistory(Customer c) {

        if (c == null) {
            throw new RuntimeException("User not authenticated");
        }

        List<Object[]> data = invoiceRepo.getMonthlySpending(c);

        return data.stream()
                .map(obj -> {
                    int monthNumber = ((Number) obj[0]).intValue();
                    int amount = ((Number) obj[1]).intValue();

                    // 🔥 Convert 1 → Jan, 2 → Feb
                    String monthName = Month.of(monthNumber).name().substring(0, 3);

                    return new SpendingHistoryDTO(monthName, amount);
                })
                .collect(Collectors.toList());
    }

    // ✅ 4. SERVICE BREAKDOWN (Pie Chart)
    public List<ServiceBreakdownDTO> getBreakdown(Customer c) {

        if (c == null) {
            throw new RuntimeException("User not authenticated");
        }

        List<Object[]> data = jobRepo.getServiceBreakdown(c);

        return data.stream()
                .map(obj -> new ServiceBreakdownDTO(
                        obj[0] != null ? obj[0].toString() : "Unknown",
                        ((Number) obj[1]).intValue()
                ))
                .collect(Collectors.toList());
    }
}
package com.gms.backend.customer.service;

import com.gms.backend.customer.dto.*;
import com.gms.backend.customer.entity.ServiceHistory;
import com.gms.backend.customer.repository.ReportsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportsService {

    @Autowired
    private ReportsRepository repo;

    // ==================  MONTHLY SPENDING ==================
    public List<MonthlySpendingDTO> monthly(Integer months) {

        LocalDate startDate = LocalDate.now()
                .minusMonths(months - 1) //  (exact months)
                .withDayOfMonth(1);

        LocalDate endDate = LocalDate.now();

        List<ServiceHistory> list =
                repo.findByServiceDateBetween(startDate, endDate);

        // Clean data
        list = list.stream()
                .filter(s -> s.getServiceDate() != null && s.getAmount() != null)
                .toList();

        // Group by month
        Map<YearMonth, Double> grouped =
                list.stream().collect(Collectors.groupingBy(
                        s -> YearMonth.from(s.getServiceDate()),
                        Collectors.summingDouble(ServiceHistory::getAmount)
                ));

        // Prepare response with sorted months
        return grouped.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // sorted months
                .map(e -> new MonthlySpendingDTO(
                        e.getKey().toString(),
                        e.getValue()
                ))
                .collect(Collectors.toList());
    }

    // ================== AVERAGE COST ==================
    public AverageCostDTO average(Integer months) {

        LocalDate startDate = LocalDate.now()
                .minusMonths(months - 1)
                .withDayOfMonth(1);

        List<ServiceHistory> list =
                repo.findByServiceDateGreaterThanEqual(startDate);

        double avg = list.stream()
                .filter(s -> s.getAmount() != null)
                .mapToDouble(ServiceHistory::getAmount)
                .average()
                .orElse(0);

        return new AverageCostDTO(avg);
    }

    // ================== SERVICE HISTORY ==================
    public List<ServiceHistoryDTO> history(Integer months) {

        LocalDate startDate = LocalDate.now()
                .minusMonths(months - 1)
                .withDayOfMonth(1);

        List<ServiceHistory> list =
                repo.findByServiceDateGreaterThanEqual(startDate);

        return list.stream()
                .filter(s -> s.getServiceDate() != null)
                .sorted(Comparator.comparing(ServiceHistory::getServiceDate).reversed())
                .map(s -> new ServiceHistoryDTO(
                        s.getId(),
                        s.getServiceName(),
                        s.getBranch(),
                        s.getAmount(),
                        s.getRating(),
                        s.getServiceDate()
                ))
                .collect(Collectors.toList());
    }

    // ================== RECEIPT ==================
    public ServiceHistory getReceipt(Long id) {
        return repo.findById(id).orElse(null);
    }

    // ==================  UPDATE RATING ==================
    @Transactional
    public ServiceHistory updateRating(Long id, Integer rating) {

        int updated = repo.updateRatingField(id, rating);

        if (updated == 0) {
            throw new RuntimeException("Record not found: " + id);
        }

        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found: " + id));
    }

    // ================== UPDATE SERVICE  ==================
    @Transactional
    public ServiceHistoryDTO updateService(Long id, ServiceHistoryDTO dto) {

        int updated = repo.updateServiceFields(
                id,
                dto.getServiceName(),
                dto.getBranch(),
                dto.getAmount(),
                dto.getRating() //
        );

        if (updated == 0) {
            throw new RuntimeException("Record not found: " + id);
        }

        ServiceHistory saved = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found: " + id));

        return new ServiceHistoryDTO(
                saved.getId(),
                saved.getServiceName(),
                saved.getBranch(),
                saved.getAmount(),
                saved.getRating(),
                saved.getServiceDate()
        );
    }

    // ==================  DELETE ==================
    @Transactional
    public void deleteRecord(Long id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("Record not found: " + id);
        }

        repo.deleteById(id);
        repo.flush();
    }

    // ==================  GET ALL ==================
    public List<ServiceHistory> getAll() {
        return repo.findAll();
    }

    // ================== PDF ==================
    public byte[] exportPdf(List<ServiceHistory> list) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            com.itextpdf.kernel.pdf.PdfWriter writer =
                    new com.itextpdf.kernel.pdf.PdfWriter(baos);

            com.itextpdf.kernel.pdf.PdfDocument pdf =
                    new com.itextpdf.kernel.pdf.PdfDocument(writer);

            com.itextpdf.layout.Document document =
                    new com.itextpdf.layout.Document(pdf);

            document.add(new com.itextpdf.layout.element.Paragraph("SERVICE REPORT"));

            for (ServiceHistory s : list) {
                document.add(new com.itextpdf.layout.element.Paragraph(
                        s.getServiceName() + " - " + s.getAmount()
                ));
            }

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("PDF Error");
        }
    }
}
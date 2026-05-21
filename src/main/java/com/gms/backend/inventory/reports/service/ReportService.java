package com.gms.backend.inventory.reports.service;

import com.gms.backend.inventory.reports.dto.*;
import com.gms.backend.inventory.reports.entity.ExportLog;
import com.gms.backend.inventory.reports.entity.ReportEmailLog;
import com.gms.backend.inventory.reports.repository.ExportLogRepository;
import com.gms.backend.inventory.reports.repository.ReportEmailLogRepository;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import lombok.RequiredArgsConstructor;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;@Service
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class ReportService {

    private final EntityManager entityManager;

     private final JavaMailSender mailSender;

    private final ReportEmailLogRepository reportEmailLogRepository;

    private final ExportLogRepository exportLogRepository;

    // API 1
    public InventoryValuationSummaryDto getInventoryValuationSummary() {

        String sql = """
                SELECT
                COALESCE(SUM(s.current_stock * i.cost_price),0),
                COALESCE(SUM(s.current_stock * i.selling_price),0)
                FROM items i
                JOIN inventory_stock s
                ON i.id = s.item_id
                """;

        Query query = entityManager.createNativeQuery(sql);

        Object[] result = (Object[]) query.getSingleResult();

        BigDecimal inventoryValue = (BigDecimal) result[0];

        BigDecimal revenue = (BigDecimal) result[1];

        return InventoryValuationSummaryDto.builder()
                .totalInventoryValue(inventoryValue)
                .potentialRevenue(revenue)
                .potentialProfit(revenue.subtract(inventoryValue))
                .currency("INR")
                .build();
    }

    // API 2
    public List<CategoryWiseValuationDto> getCategoryWiseValuation() {

        String sql = """
                SELECT
                c.category_name,
                COUNT(i.id),
                SUM(s.current_stock * i.cost_price),
                c.color_code
                FROM categories c
                JOIN items i ON c.id = i.category_id
                JOIN inventory_stock s ON i.id = s.item_id
                GROUP BY c.category_name, c.color_code
                """;

        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> results = query.getResultList();

        BigDecimal total = results.stream()
                .map(r -> (BigDecimal) r[2])
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<CategoryWiseValuationDto> response = new ArrayList<>();

        for (Object[] row : results) {

            BigDecimal value = (BigDecimal) row[2];

            double percentage =
                    value.doubleValue() * 100 / total.doubleValue();

            response.add(
                    CategoryWiseValuationDto.builder()
                            .category((String) row[0])
                            .items(((Number) row[1]).longValue())
                            .value(value)
                            .percentage(percentage)
                            .color((String) row[3])
                            .build()
            );
        }

        return response;
    }

    // API 3
    public List<TopHighValueItemDto> getTopHighValueItems(int limit) {

        String sql = """
                SELECT
                i.item_name,
                i.sku,
                s.current_stock,
                i.cost_price,
                (s.current_stock * i.cost_price) total_value,
                i.image_url
                FROM items i
                JOIN inventory_stock s
                ON i.id = s.item_id
                ORDER BY total_value DESC
                LIMIT :limit
                """;

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("limit", limit);

        List<Object[]> results = query.getResultList();

        List<TopHighValueItemDto> response = new ArrayList<>();

        int rank = 1;

        for (Object[] row : results) {

            response.add(
                    TopHighValueItemDto.builder()
                            .rank(rank++)
                            .itemName((String) row[0])
                            .sku((String) row[1])
                            .stock((Integer) row[2])
                            .unitCost((BigDecimal) row[3])
                            .totalValue((BigDecimal) row[4])
                            .imageUrl((String) row[5])
                            .build()
            );
        }

        return response;
    }

    // API 4
    public List<FastMovingItemDto> getFastMovingItems() {

        String sql = """
                SELECT
                i.item_name,
                i.sku,
                c.category_name,
                s.current_stock,
                SUM(soi.quantity) sold_qty
                FROM sales_order_items soi
                JOIN items i ON soi.item_id = i.id
                JOIN categories c ON i.category_id = c.id
                JOIN inventory_stock s ON s.item_id = i.id
                GROUP BY i.item_name, i.sku,
                c.category_name, s.current_stock
                ORDER BY sold_qty DESC
                LIMIT 10
                """;

        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> results = query.getResultList();

        List<FastMovingItemDto> response = new ArrayList<>();

        for (Object[] row : results) {

            response.add(
                    FastMovingItemDto.builder()
                            .itemName((String) row[0])
                            .sku((String) row[1])
                            .category((String) row[2])
                            .currentStock((Integer) row[3])
                            .turnoverRate("HIGH")
                            .build()
            );
        }

        return response;
    }

    // API 5
    public StockAgingSummaryDto getStockAgingSummary() {

        String sql = """
                SELECT
                SUM(CASE WHEN DATEDIFF(CURDATE(), received_date) <= 30 THEN 1 ELSE 0 END),
                SUM(CASE WHEN DATEDIFF(CURDATE(), received_date) BETWEEN 31 AND 60 THEN 1 ELSE 0 END),
                SUM(CASE WHEN DATEDIFF(CURDATE(), received_date) BETWEEN 61 AND 180 THEN 1 ELSE 0 END),
                SUM(CASE WHEN DATEDIFF(CURDATE(), received_date) > 180 THEN 1 ELSE 0 END)
                FROM stock_batches
                """;

        Query query = entityManager.createNativeQuery(sql);

        Object[] row = (Object[]) query.getSingleResult();

        return StockAgingSummaryDto.builder()
                .days0to30(((Number) row[0]).longValue())
                .days30to60(((Number) row[1]).longValue())
                .days60to180(((Number) row[2]).longValue())
                .daysAbove180(((Number) row[3]).longValue())
                .build();
    }

    // API 6
    public List<AgingBreakdownDto> getAgingBreakdown() {

        String sql = """
                SELECT
                CASE
                    WHEN DATEDIFF(CURDATE(), received_date) <= 30 THEN '0-30'
                    WHEN DATEDIFF(CURDATE(), received_date) <= 60 THEN '30-60'
                    WHEN DATEDIFF(CURDATE(), received_date) <= 180 THEN '60-180'
                    ELSE '180+'
                END aging_range,
                COUNT(*)
                FROM stock_batches
                GROUP BY aging_range
                """;

        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> results = query.getResultList();

        long total = results.stream()
                .mapToLong(r -> ((Number) r[1]).longValue())
                .sum();

        List<AgingBreakdownDto> response = new ArrayList<>();

        for (Object[] row : results) {

            long count = ((Number) row[1]).longValue();

            response.add(
                    AgingBreakdownDto.builder()
                            .range((String) row[0])
                            .count(count)
                            .percentage((count * 100.0) / total)
                            .build()
            );
        }

        return response;
    }

    // API 7
    public ResponseEntity<ByteArrayResource> exportExcel() throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Reports");

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("Item");
        header.createCell(1).setCellValue("SKU");
        header.createCell(2).setCellValue("Stock");

        List<TopHighValueItemDto> items =
                getTopHighValueItems(10);

        int rowNum = 1;

        for (TopHighValueItemDto item : items) {

            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(item.getItemName());
            row.createCell(1).setCellValue(item.getSku());
            row.createCell(2).setCellValue(item.getStock());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        workbook.write(out);

        workbook.close();

        exportLogRepository.save(
                ExportLog.builder()
                        .exportType("EXCEL")
                        .exportedBy("SYSTEM")
                        .exportedAt(LocalDateTime.now())
                        .build()
        );

        ByteArrayResource resource =
                new ByteArrayResource(out.toByteArray());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=inventory-report.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }

    // API 8
    public ResponseEntity<ByteArrayResource> exportPdf() throws Exception {

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);

        document.open();

        document.add(new Paragraph("Inventory Report"));

        PdfPTable table = new PdfPTable(3);

        table.addCell("Item");
        table.addCell("SKU");
        table.addCell("Stock");

        List<TopHighValueItemDto> items =
                getTopHighValueItems(10);

        for (TopHighValueItemDto item : items) {

            table.addCell(item.getItemName());
            table.addCell(item.getSku());
            table.addCell(item.getStock().toString());
        }

        document.add(table);

        document.close();

        exportLogRepository.save(
                ExportLog.builder()
                        .exportType("PDF")
                        .exportedBy("SYSTEM")
                        .exportedAt(LocalDateTime.now())
                        .build()
        );

        ByteArrayResource resource =
                new ByteArrayResource(out.toByteArray());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=inventory-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(resource.contentLength())
                .body(resource);
    }

    // API 9
    public String sendEmailReport(EmailReportRequestDto request)
            throws Exception {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(message, true);

        helper.setTo(request.getEmail());

        helper.setSubject("Inventory Report");

        helper.setText("Inventory report generated successfully.");

        mailSender.send(message);

        reportEmailLogRepository.save(
                ReportEmailLog.builder()
                        .email(request.getEmail())
                        .reportType(request.getReportType())
                        .sentStatus("SUCCESS")
                        .sentAt(LocalDateTime.now())
                        .build()
        );

        return "Email Sent Successfully";
    }
}


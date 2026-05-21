package com.gms.backend.inventory.reports.controller;

import com.gms.backend.inventory.reports.dto.*;
import com.gms.backend.inventory.reports.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/inventory-valuation-summary")
    public ResponseEntity<InventoryValuationSummaryDto>
    getInventorySummary() {

        return ResponseEntity.ok(
                reportService.getInventoryValuationSummary()
        );
    }

    @GetMapping("/category-wise-valuation")
    public ResponseEntity<List<CategoryWiseValuationDto>>
    getCategoryWiseValuation() {

        return ResponseEntity.ok(
                reportService.getCategoryWiseValuation()
        );
    }

    @GetMapping("/top-high-value-items")
    public ResponseEntity<List<TopHighValueItemDto>>
    getTopHighValueItems(
            @RequestParam(defaultValue = "10") int limit
    ) {

        return ResponseEntity.ok(
                reportService.getTopHighValueItems(limit)
        );
    }

    @GetMapping("/fast-moving-items")
    public ResponseEntity<List<FastMovingItemDto>>
    getFastMovingItems() {

        return ResponseEntity.ok(
                reportService.getFastMovingItems()
        );
    }

    @GetMapping("/stock-aging-summary")
    public ResponseEntity<StockAgingSummaryDto>
    getStockAgingSummary() {

        return ResponseEntity.ok(
                reportService.getStockAgingSummary()
        );
    }

    @GetMapping("/aging-breakdown")
    public ResponseEntity<List<AgingBreakdownDto>>
    getAgingBreakdown() {

        return ResponseEntity.ok(
                reportService.getAgingBreakdown()
        );
    }

    @GetMapping("/export/excel")
    public ResponseEntity<ByteArrayResource>
    exportExcel() throws Exception {

        return reportService.exportExcel();
    }

    @GetMapping("/export/pdf")
    public ResponseEntity<ByteArrayResource>
    exportPdf() throws Exception {

        return reportService.exportPdf();
    }

    @PostMapping("/email")
    public ResponseEntity<String>
    sendEmailReport(
            @RequestBody EmailReportRequestDto request
    ) throws Exception {

        return ResponseEntity.ok(
                reportService.sendEmailReport(request)
        );
    }
}

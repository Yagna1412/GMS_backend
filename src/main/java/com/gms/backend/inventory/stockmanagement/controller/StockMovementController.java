package com.gms.backend.inventory.stockmanagement.controller;

import com.gms.backend.inventory.stockmanagement.dto.*;
import com.gms.backend.inventory.stockmanagement.service.StockMovementService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService service;

    // GET /api/stock/summary
    // Powers: Total Movements, Inward, Outward, Adjustments cards
    @GetMapping("/summary")
    public ResponseEntity<StockSummaryDTO> getSummary() {
        return ResponseEntity.ok(service.getSummary());
    }

    // GET /api/stock/movements
    // Powers: main table — all rows newest first
    @GetMapping("/movements")
    public ResponseEntity<List<StockMovementDto>> getAllMovements() {
        return ResponseEntity.ok(service.getAllMovements());
    }

    // GET /api/stock/movements/search?q=
    // Powers: search bar — SKU, item name, reference number
    @GetMapping("/movements/search")
    public ResponseEntity<List<StockMovementDto>> search(@RequestParam String q) {
        return ResponseEntity.ok(service.search(q));
    }

    // GET /api/stock/movements/filter?type=INWARD|OUTWARD|ADJUSTMENT
    // Powers: All Movement Types dropdown
    @GetMapping("/movements/filter")
    public ResponseEntity<List<StockMovementDto>> filterByType(@RequestParam String type) {
        return ResponseEntity.ok(service.getByType(type));
    }

    // POST /api/stock/grn
    // Powers: GRN / Inward green button
    // TEMPORARY: createdBy hardcoded — LATER replace with Security context
    @PostMapping("/grn")
    public ResponseEntity<StockMovementDto> createGrn(
            @Valid @RequestBody GrnCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createGrn(dto, "Inventory Manager"));
        // LATER: replace "Inventory Manager" with:
        // SecurityContextHolder.getContext().getAuthentication().getName()
    }

    // POST /api/stock/issue
    // Powers: Issue Parts red button
    // TEMPORARY: createdBy hardcoded — LATER replace with Security context
    @PostMapping("/issue")
    public ResponseEntity<StockMovementDto> createIssueParts(
            @Valid @RequestBody IssuePartsCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createIssueParts(dto, "Inventory Manager"));
    }

    // POST /api/stock/adjustment
    // Powers: Adjustment orange button — quantity can be negative
    // TEMPORARY: createdBy hardcoded — LATER replace with Security context
    @PostMapping("/adjustment")
    public ResponseEntity<StockMovementDto> createAdjustment(
            @Valid @RequestBody AdjustmentCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createAdjustment(dto, "Inventory Manager"));
    }

    // GET /api/stock/movements/export?type= (type is optional)
    // Powers: Export button — downloads CSV
//    @GetMapping("/movements/export")
//    public void exportCsv(
//            @RequestParam(required = false) String type,
//            HttpServletResponse response) throws IOException {
//
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=stock_movements.csv");
//
//        List<StockMovementDto> movements = service.getAllForExport(type);
//        PrintWriter writer = response.getWriter();
//
//        writer.println("Date & Time,Item SKU,Item Name,Quantity,Movement Type,Reference #,Created By,Notes");
//        for (StockMovementDto m : movements) {
//            writer.printf("%s,%s,%s,%d,%s,%s,%s,%s%n",
//                    m.getDateTime(),
//                    m.getItemSku(),
//                    m.getItemName(),
//                    m.getQuantity(),
//                    m.getMovementType(),
//                    m.getReferenceNumber(),
//                    m.getCreatedBy(),
//                    m.getNotes() != null ? m.getNotes() : ""
//            );
//        }
//        writer.flush();
//    }

    // GET /api/stock/items/dropdown
    // Powers: Select an item dropdown in all 3 modals
    // TEMPORARY: returns hardcoded list — LATER returns from ItemRepository
    @GetMapping("/items/dropdown")
    public ResponseEntity<List<ItemDropdownDto>> getItemsDropdown() {
        return ResponseEntity.ok(service.getItemsForDropdown());
    }
}
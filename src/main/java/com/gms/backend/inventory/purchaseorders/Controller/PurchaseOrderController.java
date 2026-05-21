package com.gms.backend.inventory.purchaseorders.Controller;

import com.gms.backend.inventory.purchaseorders.Dto.PurchaseOrderDto.*;
import com.gms.backend.inventory.purchaseorders.Service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    // ─── API 1: GET /purchase-orders/stats ───────────────────────────────────
    // Dashboard summary cards (Total POs, Pending, Approved, Received)
    @GetMapping("/purchase-orders/stats")
    public ResponseEntity<PurchaseOrderStatsResponse> getStats() {
        return ResponseEntity.ok(purchaseOrderService.getStats());
    }

    // ─── API 2/3/4: GET /purchase-orders ─────────────────────────────────────
    // List all, filter by ?status=, search by ?search=, or both combined
    @GetMapping("/purchase-orders")
    public ResponseEntity<List<PurchaseOrderListResponse>> getAllPurchaseOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders(status, search));
    }

    // ─── API 5: GET /purchase-orders/{id} ────────────────────────────────────
    // Full PO details modal (eye icon click)
    @GetMapping("/purchase-orders/{id}")
    public ResponseEntity<PurchaseOrderDetailResponse> getPurchaseOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.getPurchaseOrderById(id));
    }

    // ─── API 6: POST /purchase-orders ────────────────────────────────────────
    // Create PO from modal form
    @PostMapping("/purchase-orders")
    public ResponseEntity<CreatePurchaseOrderResponse> createPurchaseOrder(
            @RequestBody CreatePurchaseOrderRequest request) {
        return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(request));
    }

    // ─── API 7: DELETE /purchase-orders/{id} ─────────────────────────────────
    // Trash icon delete
    @DeleteMapping("/purchase-orders/{id}")
    public ResponseEntity<MessageResponse> deletePurchaseOrder(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.deletePurchaseOrder(id));
    }

    // ─── API 8: GET /vendors/dropdown ────────────────────────────────────────
    // Populate Select Vendor dropdown in Create PO modal
    @GetMapping("/vendors/dropdown")
    public ResponseEntity<List<VendorDropdownResponse>> getVendorDropdown() {
        return ResponseEntity.ok(purchaseOrderService.getVendorDropdown());
    }

    // ─── API 9: GET /items/dropdown ──────────────────────────────────────────
    // Populate Select Item dropdown in Create PO modal
    @GetMapping("/items/dropdown")
    public ResponseEntity<List<ItemDropdownResponse>> getItemDropdown() {
        return ResponseEntity.ok(purchaseOrderService.getItemDropdown());
    }
}
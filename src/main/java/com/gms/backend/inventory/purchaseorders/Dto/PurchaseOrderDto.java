package com.gms.backend.inventory.purchaseorders.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// ─────────────────────────────────────────────────────────────────────────────
// REQUEST DTOs
// ─────────────────────────────────────────────────────────────────────────────

public class PurchaseOrderDto {

    // POST /purchase-orders — request body
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatePurchaseOrderRequest {
        private Long vendorId;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate expectedDeliveryDate;

        private List<LineItemRequest> items;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LineItemRequest {
        private Long itemId;
        private Integer quantity;
        private BigDecimal unitPrice;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // RESPONSE DTOs
    // ─────────────────────────────────────────────────────────────────────────

    // GET /purchase-orders — list row
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PurchaseOrderListResponse {
        private Long id;
        private String poNumber;
        private String vendor;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate orderDate;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate expectedDeliveryDate;

        private BigDecimal totalAmount;
        private String status;
    }

    // GET /purchase-orders/{id} — detail modal
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PurchaseOrderDetailResponse {
        private Long id;
        private String poNumber;
        private String vendorName;
        private String status;
        private BigDecimal totalAmount;
        private List<LineItemResponse> lineItems;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LineItemResponse {
        private String itemName;
        private Integer quantity;
        private BigDecimal price;
        private BigDecimal total;
    }

    // POST /purchase-orders — creation response
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatePurchaseOrderResponse {
        private Long id;
        private String poNumber;
        private String status;
        private BigDecimal totalAmount;
        private String message;
    }

    // GET /purchase-orders/stats — dashboard summary cards
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PurchaseOrderStatsResponse {
        private long totalPurchaseOrders;
        private long pending;
        private long pendingApproval;
        private long received;
    }

    // DELETE /purchase-orders/{id} — simple message
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageResponse {
        private String message;
    }

    // GET /vendors/dropdown
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VendorDropdownResponse {
        private Long id;
        private String vendorName;
    }

    // GET /items/dropdown
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemDropdownResponse {
        private Long id;
        private String itemName;
        private BigDecimal unitPrice;
    }
}
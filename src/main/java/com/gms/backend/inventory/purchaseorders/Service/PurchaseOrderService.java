package com.gms.backend.inventory.purchaseorders.Service;

import com.gms.backend.inventory.items.Entity.ItemEntity;
import com.gms.backend.inventory.items.Repo.ItemRepository;
import com.gms.backend.inventory.purchaseorders.Dto.PurchaseOrderDto;
import com.gms.backend.inventory.purchaseorders.Dto.PurchaseOrderDto.*;
import com.gms.backend.inventory.purchaseorders.Entity.PurchaseOrderEntity;
import com.gms.backend.inventory.purchaseorders.Entity.PurchaseOrderItemEntity;
import com.gms.backend.inventory.purchaseorders.Entity.PurchaseOrderStatus;
import com.gms.backend.inventory.purchaseorders.Repo.PurchaseOrderRepository;
import com.gms.backend.inventory.vendors.Entity.VendorEntity;
import com.gms.backend.inventory.vendors.Repo.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final VendorRepository vendorRepository;
    private final ItemRepository itemRepository;

    // ─── Stats ───────────────────────────────────────────────────────────────

    public PurchaseOrderStatsResponse getStats() {
        long total       = purchaseOrderRepository.count();
        long pending     = purchaseOrderRepository.countByStatus(PurchaseOrderStatus.PENDING);
        long pendingAppr = purchaseOrderRepository.countByStatus(PurchaseOrderStatus.PENDING_APPROVAL);
        long received    = purchaseOrderRepository.countByStatus(PurchaseOrderStatus.RECEIVED);
        return new PurchaseOrderStatsResponse(total, pending, pendingAppr, received);
    }

    // ─── List / Search / Filter ───────────────────────────────────────────────

    public List<PurchaseOrderListResponse> getAllPurchaseOrders(String status, String search) {
        List<PurchaseOrderEntity> orders;

        boolean hasStatus = status != null && !status.isBlank();
        boolean hasSearch = search != null && !search.isBlank();

        if (hasStatus && hasSearch) {
            PurchaseOrderStatus statusEnum = PurchaseOrderStatus.valueOf(status.toUpperCase());
            // .name() converts enum → String because native query expects String, not enum
            orders = purchaseOrderRepository.findByStatusAndSearch(statusEnum.name(), search);
        } else if (hasStatus) {
            PurchaseOrderStatus statusEnum = PurchaseOrderStatus.valueOf(status.toUpperCase());
            orders = purchaseOrderRepository.findByStatus(statusEnum);
        } else if (hasSearch) {
            orders = purchaseOrderRepository.searchByPoNumberOrVendorName(search);
        } else {
            orders = purchaseOrderRepository.findAll();
        }

        return orders.stream()
                .map(this::toListResponse)
                .collect(Collectors.toList());
    }

    // ─── Detail ───────────────────────────────────────────────────────────────

    public PurchaseOrderDetailResponse getPurchaseOrderById(Long id) {
        PurchaseOrderEntity po = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found with id: " + id));

        VendorEntity vendor = vendorRepository.findById(po.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        List<LineItemResponse> lineItems = po.getLineItems().stream()
                .map(item -> {
                    ItemEntity itemEntity = itemRepository.findById(item.getItemId())
                            .orElseThrow(() -> new RuntimeException("Item not found"));
                    return new LineItemResponse(
                            itemEntity.getItemName(),
                            item.getQuantity(),
                            item.getUnitPrice(),
                            item.getTotalPrice()
                    );
                })
                .collect(Collectors.toList());

        return new PurchaseOrderDetailResponse(
                po.getId(),
                po.getPoNumber(),
                vendor.getVendorName(),
                po.getStatus().name(),
                po.getTotalAmount(),
                lineItems
        );
    }

    // ─── Create ───────────────────────────────────────────────────────────────

    @Transactional
    public CreatePurchaseOrderResponse createPurchaseOrder(CreatePurchaseOrderRequest request) {
        // Validate vendor exists
        VendorEntity vendor = vendorRepository.findById(request.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + request.getVendorId()));

        // Build entity
        PurchaseOrderEntity po = new PurchaseOrderEntity();
        po.setVendorId(request.getVendorId());
        po.setOrderDate(LocalDate.now());
        po.setExpectedDeliveryDate(request.getExpectedDeliveryDate());
        po.setStatus(PurchaseOrderStatus.PENDING);

        // Build line items and calculate total
        BigDecimal total = BigDecimal.ZERO;
        for (LineItemRequest itemReq : request.getItems()) {
            ItemEntity itemEntity = itemRepository.findById(itemReq.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemReq.getItemId()));

            BigDecimal lineTotal = itemReq.getUnitPrice()
                    .multiply(BigDecimal.valueOf(itemReq.getQuantity()));
            total = total.add(lineTotal);

            PurchaseOrderItemEntity lineItem = new PurchaseOrderItemEntity();
            lineItem.setPurchaseOrder(po);
            lineItem.setItemId(itemReq.getItemId());
            lineItem.setQuantity(itemReq.getQuantity());
            lineItem.setUnitPrice(itemReq.getUnitPrice());
            lineItem.setTotalPrice(lineTotal);
            po.getLineItems().add(lineItem);
        }

        po.setTotalAmount(total);
        po.setPoNumber(generatePoNumber());

        PurchaseOrderEntity saved = purchaseOrderRepository.save(po);

        return new CreatePurchaseOrderResponse(
                saved.getId(),
                saved.getPoNumber(),
                saved.getStatus().name(),
                saved.getTotalAmount(),
                "Purchase Order Created Successfully"
        );
    }

    // ─── Delete ───────────────────────────────────────────────────────────────

    @Transactional
    public MessageResponse deletePurchaseOrder(Long id) {
        if (!purchaseOrderRepository.existsById(id)) {
            throw new RuntimeException("Purchase Order not found with id: " + id);
        }
        purchaseOrderRepository.deleteById(id);
        return new MessageResponse("Purchase Order Deleted Successfully");
    }

    // ─── Vendor Dropdown ─────────────────────────────────────────────────────

    public List<VendorDropdownResponse> getVendorDropdown() {
        return vendorRepository.findAll().stream()
                .map(v -> new VendorDropdownResponse(v.getId(), v.getVendorName()))
                .collect(Collectors.toList());
    }

    // ─── Item Dropdown ───────────────────────────────────────────────────────

    public List<ItemDropdownResponse> getItemDropdown() {
        return itemRepository.findAll().stream()
                .map(i -> new ItemDropdownResponse(i.getId(), i.getItemName(), i.getUnitPrice()))
                .collect(Collectors.toList());
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    private String generatePoNumber() {
        int year = LocalDate.now().getYear();
        long countThisYear = purchaseOrderRepository.countByYear(year);
        return String.format("PO-%d-%03d", year, countThisYear + 1);
    }

    private PurchaseOrderListResponse toListResponse(PurchaseOrderEntity po) {
        String vendorName = vendorRepository.findById(po.getVendorId())
                .map(VendorEntity::getVendorName)
                .orElse("Unknown Vendor");

        return new PurchaseOrderListResponse(
                po.getId(),
                po.getPoNumber(),
                vendorName,
                po.getOrderDate(),
                po.getExpectedDeliveryDate(),
                po.getTotalAmount(),
                po.getStatus().name()
        );
    }
}
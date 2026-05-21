package com.gms.backend.inventory.dashboard.service;

import com.gms.backend.inventory.dashboard.repository.InventoryItemRepository;
import com.gms.backend.inventory.dashboard.repository.NotificationRepository;
import com.gms.backend.inventory.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final InventoryItemRepository inventoryItemRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public Object getSummary() {

        Map<String, Object> response = new HashMap<>();

        response.put("totalInventoryValue", 250000);
        response.put("currency", "₹");
        response.put("valueChangePercent", 12);
        response.put("totalActiveItems", 120);
        response.put("itemsChangeDelta", 5);
        response.put("lowStockAlerts", 8);
        response.put("lowStockStatus", "warning");
        response.put("outOfStockItems", 2);
        response.put("outOfStockStatus", "critical");

        return response;
    }

    @Override
    public Object getStockStatus() {

        Map<String, Object> response = new HashMap<>();

        response.put("optimal", 80);
        response.put("lowStock", 25);
        response.put("critical", 5);
        response.put("overstock", 10);

        return response;
    }

    @Override
    public Object getPendingActions() {

        Map<String, Object> response = new HashMap<>();

        response.put("purchaseOrders", 12);
        response.put("poApprovals", 5);
        response.put("vendorInvoices", 7);
        response.put("stockAudits", 2);

        return response;
    }

    @Override
    public Object getLowStockItems() {

        return inventoryItemRepository.findByStockStatus("LOW");
    }

    @Override
    public Object getOutOfStockItems() {

        return inventoryItemRepository.findByCurrentStock(0);
    }

    @Override
    public Object search(String q) {

        return inventoryItemRepository
                .findByItemNameContainingIgnoreCase(q);
    }

    @Override
    public Object getNotifications(boolean isRead) {

        return notificationRepository.findByIsRead(isRead);
    }
}
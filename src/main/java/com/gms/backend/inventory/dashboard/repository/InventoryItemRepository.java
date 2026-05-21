package com.gms.backend.inventory.dashboard.repository;

import com.gms.backend.inventory.dashboard.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository
        extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findByStockStatus(String stockStatus);

    List<InventoryItem> findByCurrentStock(int currentStock);

    List<InventoryItem> findByItemNameContainingIgnoreCase(String itemName);
}
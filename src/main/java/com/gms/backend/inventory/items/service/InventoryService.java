package com.gms.backend.inventory.items.service;

import com.gms.backend.inventory.items.dto.InventoryRequestDTO;
import com.gms.backend.inventory.items.dto.InventorySummaryDTO;
import com.gms.backend.inventory.items.entity.InventoryItem;
import com.gms.backend.inventory.items.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    // SUMMARY
    public InventorySummaryDTO getSummary() {

        InventorySummaryDTO dto =
                new InventorySummaryDTO();

        dto.setTotalItems(repository.count());

        dto.setActiveItems(repository.count());

        dto.setLowStock(
                repository.countByStatus("Low Stock")
        );

        dto.setOutOfStock(
                repository.countByStatus("Out of Stock")
        );

        return dto;
    }

    // GET ALL ITEMS
    public List<InventoryItem> getAllItems() {
        return repository.findAll();
    }

    // GET SINGLE ITEM
    public InventoryItem getItem(Integer id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Item Not Found"));
    }

    // ADD ITEM
    public InventoryItem addItem(
            InventoryRequestDTO dto
    ) {

        InventoryItem item =
                new InventoryItem();

        item.setSku(dto.getSku());
        item.setItemName(dto.getItemName());
        item.setCategory(dto.getCategory());
        item.setCostPrice(dto.getCostPrice());
        item.setSellingPrice(dto.getSellingPrice());
        item.setStockQuantity(dto.getStockQuantity());
        item.setMinStock(dto.getMinStock());
        item.setMaxStock(dto.getMaxStock());
        item.setImageUrl(dto.getImageUrl());

        item.setStatus(
                calculateStatus(
                        dto.getStockQuantity(),
                        dto.getMinStock(),
                        dto.getMaxStock()
                )
        );

        return repository.save(item);
    }

    // UPDATE ITEM
    public InventoryItem updateItem(
            Integer id,
            InventoryRequestDTO dto
    ) {

        InventoryItem item =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Item Not Found"));

        item.setSku(dto.getSku());
        item.setItemName(dto.getItemName());
        item.setCategory(dto.getCategory());
        item.setCostPrice(dto.getCostPrice());
        item.setSellingPrice(dto.getSellingPrice());
        item.setStockQuantity(dto.getStockQuantity());
        item.setMinStock(dto.getMinStock());
        item.setMaxStock(dto.getMaxStock());
        item.setImageUrl(dto.getImageUrl());

        item.setStatus(
                calculateStatus(
                        dto.getStockQuantity(),
                        dto.getMinStock(),
                        dto.getMaxStock()
                )
        );

        return repository.save(item);
    }

    // DELETE ITEM
    public void deleteItem(Integer id) {

        InventoryItem item =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Item Not Found"));

        repository.delete(item);
    }

    // SEARCH
    public List<InventoryItem> search(
            String query
    ) {

        List<InventoryItem> items =
                repository.findByItemNameContainingIgnoreCase(query);

        if (!items.isEmpty()) {
            return items;
        }

        return repository.findBySkuContainingIgnoreCase(query);
    }

    // FILTER STATUS
    public List<InventoryItem> filterByStatus(
            String status
    ) {
        return repository.findByStatus(status);
    }

    // STATUS LOGIC
    private String calculateStatus(
            Integer stock,
            Integer min,
            Integer max
    ) {

        if (stock == 0) {
            return "Out of Stock";
        }

        if (stock < min) {
            return "Low Stock";
        }

        if (stock > max) {
            return "Overstock";
        }

        return "Optimal";
    }

    // MARKUP %
    public double calculateMarkup(
            BigDecimal costPrice,
            BigDecimal sellingPrice
    ) {

        BigDecimal markup =
                sellingPrice.subtract(costPrice)
                        .divide(costPrice, 2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));

        return markup.doubleValue();
    }
}
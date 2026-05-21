package com.gms.backend.inventory.items.controller;

import com.gms.backend.inventory.items.dto.InventoryRequestDTO;
import com.gms.backend.inventory.items.dto.InventorySummaryDTO;
import com.gms.backend.inventory.items.entity.InventoryItem;
import com.gms.backend.inventory.items.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    // DASHBOARD SUMMARY
    @GetMapping("/summary")
    public InventorySummaryDTO getSummary() {

        return service.getSummary();
    }

    // GET ALL ITEMS
    @GetMapping
    public List<InventoryItem> getAllItems() {

        return service.getAllItems();
    }

    // GET SINGLE ITEM
    @GetMapping("/{id}")
    public InventoryItem getItem(
            @PathVariable Integer id
    ) {

        return service.getItem(id);
    }

    // ADD ITEM
    @PostMapping
    public InventoryItem addItem(
            @RequestBody InventoryRequestDTO dto
    ) {

        return service.addItem(dto);
    }

    // UPDATE ITEM
    @PutMapping("/{id}")
    public InventoryItem updateItem(
            @PathVariable Integer id,
            @RequestBody InventoryRequestDTO dto
    ) {

        return service.updateItem(id, dto);
    }

    // DELETE ITEM
    @DeleteMapping("/{id}")
    public String deleteItem(
            @PathVariable Integer id
    ) {

        service.deleteItem(id);

        return "Inventory Item Deleted Successfully";
    }

    // SEARCH
    @GetMapping("/search")
    public List<InventoryItem> search(
            @RequestParam String query
    ) {

        return service.search(query);
    }

    // FILTER BY STATUS
    @GetMapping("/status/{status}")
    public List<InventoryItem> filterByStatus(
            @PathVariable String status
    ) {

        return service.filterByStatus(status);
    }
}
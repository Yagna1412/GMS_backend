package com.gms.backend.inventory.vendors.controller;

import com.gms.backend.inventory.vendors.dto.DashboardResponseDto;
import com.gms.backend.inventory.vendors.dto.VendorRequestDto;
import com.gms.backend.inventory.vendors.dto.VendorResponseDto;
import com.gms.backend.inventory.vendors.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/vendors")
@CrossOrigin()

public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping
    public VendorResponseDto createVendor(@RequestBody VendorRequestDto dto) {

        return vendorService.createVendor(dto);
    }

    @GetMapping
    public List<VendorResponseDto> getAllVendors(
            @RequestParam(required = false) String tier
    ) {

        if (tier != null && !tier.isEmpty()) {
            return vendorService.filterByTier(tier);
        }

        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    public VendorResponseDto getVendorById(@PathVariable Long id) {

        return vendorService.getVendorById(id);
    }

    @PutMapping("/{id}")
    public String updateVendor(
            @PathVariable Long id,
            @RequestBody VendorRequestDto dto
    ) {

        vendorService.updateVendor(id, dto);

        return "Vendor updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteVendor(@PathVariable Long id) {

        vendorService.deleteVendor(id);

        return "Vendor deleted successfully";
    }

    @GetMapping("/search")
    public List<VendorResponseDto> searchVendors(
            @RequestParam String keyword
    ) {

        return vendorService.searchVendors(keyword);
    }

    @GetMapping("/dashboard")
    public DashboardResponseDto getDashboard() {

        return vendorService.getDashboard();
    }
}

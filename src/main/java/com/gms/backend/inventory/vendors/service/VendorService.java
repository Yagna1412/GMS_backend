package com.gms.backend.inventory.vendors.service;

import com.gms.backend.inventory.vendors.dto.DashboardResponseDto;
import com.gms.backend.inventory.vendors.dto.VendorRequestDto;
import com.gms.backend.inventory.vendors.dto.VendorResponseDto;
import com.gms.backend.inventory.vendors.entity.VendorEntity;
import com.gms.backend.inventory.vendors.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public VendorResponseDto createVendor(VendorRequestDto dto) {

        VendorEntity vendor = new VendorEntity();

        vendor.setVendorCode("VEN" + System.currentTimeMillis());

        vendor.setVendorName(dto.getVendorName());
        vendor.setPhone(dto.getPhone());
        vendor.setEmail(dto.getEmail());
        vendor.setAddress(dto.getAddress());
        vendor.setGstNumber(dto.getGstNumber());
        vendor.setPaymentTerms(dto.getPaymentTerms());
        vendor.setTier(dto.getTier());
        vendor.setStatus(dto.getStatus());

        VendorEntity savedVendor = vendorRepository.save(vendor);

        return mapToDto(savedVendor);
    }

    public List<VendorResponseDto> getAllVendors() {

        return vendorRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public VendorResponseDto getVendorById(Long id) {

        VendorEntity vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        return mapToDto(vendor);
    }

    public void updateVendor(Long id, VendorRequestDto dto) {

        VendorEntity vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setVendorName(dto.getVendorName());
        vendor.setPhone(dto.getPhone());
        vendor.setEmail(dto.getEmail());
        vendor.setAddress(dto.getAddress());
        vendor.setGstNumber(dto.getGstNumber());
        vendor.setPaymentTerms(dto.getPaymentTerms());
        vendor.setTier(dto.getTier());
        vendor.setStatus(dto.getStatus());

        vendorRepository.save(vendor);
    }

    public void deleteVendor(Long id) {

        vendorRepository.deleteById(id);
    }



    public void increaseTotalPos(Long vendorId) {

        VendorEntity vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setTotalPos(vendor.getTotalPos() + 1);

        vendorRepository.save(vendor);
    }

    public void updateRating(Long vendorId, Double rating) {

        VendorEntity vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setRating(rating);

        vendorRepository.save(vendor);
    }

    public void updateOnTimePercentage(Long vendorId, Double percentage) {

        VendorEntity vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setOnTimePercentage(percentage);

        vendorRepository.save(vendor);
    }

    public List<VendorResponseDto> searchVendors(String keyword) {

        return vendorRepository
                .findByVendorNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword
                )
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<VendorResponseDto> filterByTier(String tier) {

        return vendorRepository.findByTier(tier)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public DashboardResponseDto getDashboard() {

        Long total = vendorRepository.count();

        Long platinum = vendorRepository.countByTier("Platinum");

        Long gold = vendorRepository.countByTier("Gold");

        Long silver = vendorRepository.countByTier("Silver");

        return new DashboardResponseDto(
                total,
                platinum,
                gold,
                silver
        );
    }

    private VendorResponseDto mapToDto(VendorEntity vendor) {

        VendorResponseDto dto = new VendorResponseDto();

        dto.setId(vendor.getId());
        dto.setVendorCode(vendor.getVendorCode());
        dto.setVendorName(vendor.getVendorName());
        dto.setPhone(vendor.getPhone());
        dto.setEmail(vendor.getEmail());
        dto.setOnTimePercentage(vendor.getOnTimePercentage());
        dto.setRating(vendor.getRating());
        dto.setTotalPos(vendor.getTotalPos());
        dto.setTier(vendor.getTier());
        dto.setStatus(vendor.getStatus());

        return dto;
    }
}

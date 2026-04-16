package com.gms.backend.BookingService.controller;

import com.gms.backend.BookingService.DTO.GmsDTO;
import com.gms.backend.BookingService.entity.*;
import com.gms.backend.BookingService.service.GmsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/gms")
public class GmsController {

    private final GmsService gmsService;

    public GmsController(GmsService gmsService) {
        this.gmsService = gmsService;
    }

    //  Branch APIs
    @GetMapping("/branches")
    public List<BranchEntity> getAllBranches(@RequestParam(required = false) String query) {
        if (query != null && !query.trim().isEmpty()) {
            return gmsService.searchBranches(query.trim());
        }
        return gmsService.getAllBranches();
    }

    @GetMapping("/branches/nearby")
    public List<BranchEntity> getNearbyBranches(
            @RequestParam Double lat,
            @RequestParam Double lng,
            @RequestParam(defaultValue = "10.0") Double radius) {
        return gmsService.getNearbyBranches(lat, lng, radius);
    }

    //  Service APIs
    @GetMapping("/services")
    public List<ServiceEntity> getAllServices() {
        return gmsService.getAllServices();
    }

    // Booking APIs
    @GetMapping("/bookings")
    public List<BookingEntity> getAllBookings() {
        return gmsService.getAllBookings();
    }

    @PostMapping("/booking/select-branch")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingEntity selectBranch(@RequestBody GmsDTO dto) {
        return gmsService.selectBranch(dto);
    }

    @PostMapping("/booking/select-services")
    public BookingEntity selectServices(@RequestBody GmsDTO dto) {
        return gmsService.selectServices(dto);
    }

    @PostMapping("/booking/select-slot")
    public BookingEntity selectSlot(@RequestBody GmsDTO dto) {
        return gmsService.selectSlot(dto);
    }

    @PostMapping("/booking/confirm")
    public BookingEntity confirmBooking(@RequestBody GmsDTO dto) {
        return gmsService.confirmBooking(dto);
    }

    @GetMapping("/booking/{id}")
    public BookingEntity getBookingById(@PathVariable Long id) {
        return gmsService.getBookingById(id);
    }
}
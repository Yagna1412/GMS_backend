package com.gms.backend.BookingService.service;

import com.gms.backend.BookingService.DTO.GmsDTO;
import com.gms.backend.BookingService.entity.*;
import com.gms.backend.BookingService.repo.*;
import com.gms.backend.exception.GmsException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class GmsService {

    private final BranchRepo branchRepo;
    private final ServiceRepo serviceRepo;
    private final BookingRepo bookingRepo;

    public GmsService(BranchRepo branchRepo,
                      ServiceRepo serviceRepo,
                      BookingRepo bookingRepo) {
        this.branchRepo = branchRepo;
        this.serviceRepo = serviceRepo;
        this.bookingRepo = bookingRepo;
    }

    // ─── Branch ───
    public List<BranchEntity> getAllBranches() {
        return branchRepo.findAll();
    }

    public List<BranchEntity> searchBranches(String query) {
        return branchRepo.findByBranchNameContainingIgnoreCaseOrBranchCityContainingIgnoreCaseOrBranchZipcode(
                query, query, query
        );
    }

    public List<BranchEntity> getNearbyBranches(Double lat, Double lng, Double radius) {
        return branchRepo.findNearbyBranches(lat, lng, radius);
    }

    // ─── Services ───
    public List<ServiceEntity> getAllServices() {
        return serviceRepo.findAll();
    }

    // ─── Booking ───
    public List<BookingEntity> getAllBookings() {
        return bookingRepo.findAll();
    }

    //  Step 1 — Select Branch
    public BookingEntity selectBranch(GmsDTO dto) {
        BranchEntity branch = branchRepo.findById(dto.getBranchId())
                .orElseThrow(() -> new GmsException(HttpStatus.NOT_FOUND, "Branch not found"));

        BookingEntity booking = new BookingEntity();

        booking.setBranch(branch); // ManyToOne
        booking.setBookingStatus("BRANCH_SELECTED");
        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepo.save(booking);
    }

    //  Step 2 — Select Services
    public BookingEntity selectServices(GmsDTO dto) {
        BookingEntity booking = getBooking(dto.getBookingId(), "BRANCH_SELECTED");

        List<ServiceEntity> services = serviceRepo.findAllById(dto.getServiceIds());

        booking.setServices(services); // ManyToMany

        double total = services.stream()
                .mapToDouble(ServiceEntity::getServicePrice)
                .sum();

        booking.setTotalPrice(total); //  correct field name
        booking.setBookingStatus("SERVICES_SELECTED");

        return bookingRepo.save(booking);
    }

    //  Step 3 — Select Slot
    public BookingEntity selectSlot(GmsDTO dto) {
        BookingEntity booking = getBooking(dto.getBookingId(), "SERVICES_SELECTED");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

        LocalDate date = LocalDate.parse(dto.getDate(), dateFormatter);
        LocalTime time = LocalTime.parse(dto.getTime(), timeFormatter);

        booking.setBookingTime(LocalDateTime.of(date, time));
        booking.setBookingStatus("SLOT_SELECTED");

        return bookingRepo.save(booking);
    }

    //  Step 4 — Confirm Booking
    public BookingEntity confirmBooking(GmsDTO dto) {
        BookingEntity booking = getBooking(dto.getBookingId(), "SLOT_SELECTED");

        booking.setBookingStatus("CONFIRMED");

        return bookingRepo.save(booking);
    }

    // ─── Helper ───
    public BookingEntity getBookingById(Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new GmsException(HttpStatus.NOT_FOUND, "Booking not found"));
    }

    private BookingEntity getBooking(Long id, String expectedStatus) {
        BookingEntity booking = bookingRepo.findById(id)
                .orElseThrow(() -> new GmsException(HttpStatus.NOT_FOUND, "Booking not found"));

        if (!expectedStatus.equals(booking.getBookingStatus())) {
            throw new GmsException(HttpStatus.CONFLICT, "Invalid booking step");
        }

        return booking;
    }
}
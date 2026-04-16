package com.gms.backend.BookingService.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GmsDTO {

    // Step 1: Select Branch
    private Long branchId;

    // Step 2: Select Services
    private List<Long> serviceIds;

    // Step 3 & 4: Select Slot & Confirm
    private Long bookingId;
    private String date;        // Format: "yyyy-MM-dd"
    private String time;        // Format: "hh:mm a" (e.g., "02:30 PM")
    private Double totalPrice;
}
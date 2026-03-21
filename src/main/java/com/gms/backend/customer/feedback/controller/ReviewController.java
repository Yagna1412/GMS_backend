package com.gms.backend.customer.feedback.controller;

import com.gms.backend.customer.feedback.dto.ReviewResponseDTO;
import com.gms.backend.customer.feedback.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * GET /api/reviews?customerId=123
     *
     * Returns all reviews submitted by the given customer.
     * Used by the "My Reviews" tab on the Support & Feedback page.
     *
     * NOTE: Once you integrate JWT authentication, replace the
     * @RequestParam customerId with the value extracted from
     * the JWT token (see comment below).
     */
    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> getMyReviews(
            @RequestParam Long customerId
    ) {
        /*
         * -------------------------------------------------------
         * FUTURE: JWT Integration (when you add Spring Security)
         * -------------------------------------------------------
         * Replace @RequestParam Long customerId with this:
         *
         *   @AuthenticationPrincipal UserDetails userDetails
         *
         * Then extract the customerId like:
         *
         *   Long customerId = ((YourCustomUserDetails) userDetails).getCustomerId();
         *
         * And remove the @RequestParam above entirely.
         * -------------------------------------------------------
         */

        List<ReviewResponseDTO> reviews = reviewService.getReviewsByCustomer(customerId);
        return ResponseEntity.ok(reviews);
    }
}
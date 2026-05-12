// PromotionController.java
package com.gms.backend.customer.dashboard.controller;

import com.gms.backend.customer.dashboard.dto.PromotionDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    // 9. PROMO
    @GetMapping
    public List<PromotionDTO> getPromotions() {
        return List.of(
                new PromotionDTO("20% OFF", "AC Service Discount"),
                new PromotionDTO("Free Checkup", "Engine Checkup Free")
        );
    }
}
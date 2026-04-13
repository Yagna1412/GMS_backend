package com.gms.backend.promotion.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    @GetMapping
    public List<Map<String, String>> getPromotions() {
        return List.of(
                Map.of("title", "20% OFF", "desc", "AC Service Discount"),
                Map.of("title", "Free Checkup", "desc", "Engine Checkup Free")
        );
    }
}
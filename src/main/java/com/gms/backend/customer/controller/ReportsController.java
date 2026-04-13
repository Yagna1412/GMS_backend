package com.gms.backend.customer.controller;

import com.gms.backend.customer.dto.*;
import com.gms.backend.customer.entity.ServiceHistory;
import com.gms.backend.customer.service.ReportsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {
    RequestMethod.GET, RequestMethod.POST,
    RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS
})
public class ReportsController {

    @Autowired
    private ReportsService service;

    @GetMapping("/monthly-spending")
    public List<MonthlySpendingDTO> monthly(@RequestParam Integer months){
        return service.monthly(months);
    }

    @GetMapping("/average-cost")
    public AverageCostDTO average(@RequestParam Integer months){
        return service.average(months);
    }

    @GetMapping("/service-history")
    public List<ServiceHistoryDTO> history(@RequestParam Integer months){
        return service.history(months);
    }

    @GetMapping("/receipt/{id}")
    public ServiceHistory getReceipt(@PathVariable Long id) {
        return service.getReceipt(id);
    }

    @PutMapping("/service-history/{id}/rating")
    public ServiceHistory updateRating(
            @PathVariable Long id,
            @RequestParam Integer rating) {
        return service.updateRating(id, rating);
    }

    // Update Service
    @PutMapping("/service-history/{id}")
    public ResponseEntity<ServiceHistoryDTO> updateService(
            @PathVariable Long id,
            @RequestBody ServiceHistoryDTO request) {
        ServiceHistoryDTO updated = service.updateService(id, request);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // Delete Service
    @DeleteMapping("/service-history/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id) {
        service.deleteRecord(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
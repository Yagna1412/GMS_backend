package com.gms.backend.MyVehicle.controller;

import com.gms.backend.MyVehicle.dto.MyVehicleDto;
import com.gms.backend.MyVehicle.service.MyVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class MyVehicleController {

    private final MyVehicleService service;


    @GetMapping
    public List<MyVehicleDto> getVehicles(
            @RequestParam(required = false) String query) {
        return service.getVehicles(query);
    }


    @PostMapping
    public MyVehicleDto addVehicle(@RequestBody MyVehicleDto dto) {
        return service.addVehicle(dto);
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteVehicle(id);
        return "Vehicle Deleted";
    }


    @PutMapping("/{id}")
    public MyVehicleDto update(
            @PathVariable Long id,
            @RequestBody MyVehicleDto dto) {
        return service.updateVehicle(id, dto);
    }


    @PutMapping("/{id}/set-primary")
    public String setPrimary(@PathVariable Long id) {
        service.setPrimary(id);
        return "Primary set";
    }
}
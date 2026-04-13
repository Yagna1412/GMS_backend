package com.gms.backend.customer.dashboardcontoller;

import com.gms.backend.customer.Customer;
import com.gms.backend.customer.DashboardDTO.ProfileDTO;
import com.gms.backend.customer.DashboardDTO.VehicleDTO;
import com.gms.backend.customer.dashboardcustomerrepository.CustomerRepository;
import com.gms.backend.vehicle.VehicleRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final VehicleRepository vehicleRepo;
    private final CustomerRepository customerRepo;

    public UserController(VehicleRepository vehicleRepo, CustomerRepository customerRepo) {
        this.vehicleRepo = vehicleRepo;
        this.customerRepo = customerRepo;
    }

    @GetMapping("/vehicle")
    public VehicleDTO getVehicle() {

        Customer user = customerRepo.findById(1L).orElse(null);

        if (user == null) {
            return new VehicleDTO("No Vehicle", "N/A");
        }

        return vehicleRepo.findByCustomer_Id(user.getId())
                .map(v -> new VehicleDTO(v.getModel(), v.getPlateNo()))
                .orElse(new VehicleDTO("No Vehicle", "N/A"));
    }

    @GetMapping("/profile")
    public ProfileDTO getProfile() {

        Customer user = customerRepo.findById(1L).orElse(null);

        if (user == null) {
            return new ProfileDTO("Alex", "alex@example.com");
        }

        return new ProfileDTO(user.getName(), user.getEmail());
    }
}
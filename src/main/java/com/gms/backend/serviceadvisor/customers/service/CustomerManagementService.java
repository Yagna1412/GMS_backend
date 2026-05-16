package com.gms.backend.serviceadvisor.customers.service;

import com.gms.backend.serviceadvisor.customers.dto.CustomerDTO;
import com.gms.backend.serviceadvisor.customers.entity.Customer;
import com.gms.backend.serviceadvisor.customers.entity.Vehicle;
import com.gms.backend.serviceadvisor.customers.entity.ServiceHistory;
import com.gms.backend.serviceadvisor.customers.entity.CustomerPreference;

import com.gms.backend.serviceadvisor.customers.repository.CustomerPreferenceRepository;
import com.gms.backend.serviceadvisor.customers.repository.CustomerRepository;
import com.gms.backend.serviceadvisor.customers.repository.ServiceHistoryRepository;
import com.gms.backend.serviceadvisor.customers.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerManagementService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ServiceHistoryRepository serviceHistoryRepository;

    @Autowired
    private CustomerPreferenceRepository customerPreferenceRepository;

    // DASHBOARD STATS
    public CustomerDTO.CustomerStatsResponse getCustomerStats() {
        List<Customer> customers = customerRepository.findAll();

        long regular = customers.stream()
                .filter(c -> "Regular".equalsIgnoreCase(c.getType()))
                .count();

        long premium = customers.stream()
                .filter(c -> "Premium".equalsIgnoreCase(c.getType()))
                .count();

        long vip = customers.stream()
                .filter(c -> "VIP".equalsIgnoreCase(c.getType()))
                .count();

        CustomerDTO.CustomerStatsResponse response =
                new CustomerDTO.CustomerStatsResponse();

        response.setTotalCustomers((long) customers.size());
        response.setRegular(regular);
        response.setPremium(premium);
        response.setVip(vip);

        return response;
    }

    // GET ALL CUSTOMERS
    public List<Customer> getAllCustomers(String search, String type) {
        if (search != null && !search.isEmpty() && type != null && !type.isEmpty()) {
            return customerRepository.findByNameContainingIgnoreCaseAndType(search, type);
        } else if (search != null && !search.isEmpty()) {
            return customerRepository.findByNameContainingIgnoreCase(search);
        } else if (type != null && !type.isEmpty()) {
            return customerRepository.findByType(type);
        } else {
            return customerRepository.findAll();
        }
    }

    // CREATE CUSTOMER
    public Customer createCustomer(CustomerDTO.CustomerRequest request) {
        Customer customer = new Customer();

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setType(request.getType());

        return customerRepository.save(customer);
    }

    // GET CUSTOMER BY ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // FULL PROFILE (customer + vehicles + service history)
    public CustomerDTO.CustomerFullProfileResponse getCustomerFullProfile(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Vehicle> vehicles = vehicleRepository.findByCustomerId(customerId);

        List<ServiceHistory> serviceHistory =
                serviceHistoryRepository.findByCustomerId(customerId);

        CustomerDTO.CustomerFullProfileResponse response =
                new CustomerDTO.CustomerFullProfileResponse();

        response.setCustomer(customer);
        response.setVehicles(vehicles);
        response.setServiceHistory(serviceHistory);

        return response;
    }

    // UPDATE CUSTOMER
    public Customer updateCustomer(Long id, CustomerDTO.CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setType(request.getType());

        return customerRepository.save(customer);
    }

    // DELETE CUSTOMER
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // ADD VEHICLE
    public Vehicle addVehicle(CustomerDTO.VehicleRequest request) {
        Vehicle vehicle = new Vehicle();

        vehicle.setCustomerId(request.getCustomerId());
        vehicle.setMake(request.getMake());
        vehicle.setModel(request.getModel());
        vehicle.setYear(request.getYear());
        vehicle.setRegNo(request.getRegNo());
        vehicle.setVin(request.getVin());
        vehicle.setAddedAt(new Date());

        return vehicleRepository.save(vehicle);
    }

    // GET VEHICLES
    public List<Vehicle> getVehiclesByCustomerId(Long customerId) {
        return vehicleRepository.findByCustomerId(customerId);
    }

    // DELETE VEHICLE
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    // GET SERVICE HISTORY
    public List<ServiceHistory> getServiceHistory(Long customerId) {
        return serviceHistoryRepository.findByCustomerId(customerId);
    }

    // SAVE PREFERENCES
    public CustomerPreference savePreference(CustomerDTO.CustomerPreferenceRequest request) {
        CustomerPreference preference = new CustomerPreference();

        preference.setCustomerId(request.getCustomerId());
        preference.setPreferredTechnician(request.getPreferredTechnician());
        preference.setPreferredTime(request.getPreferredServiceTime());
        preference.setTimeSlot(request.getTimeSlot());
        preference.setCommunicationMode(request.getCommunicationMode());
        preference.setSpecialInstructions(request.getSpecialInstructions());

        return customerPreferenceRepository.save(preference);
    }

    // GET PREFERENCES
    public List<CustomerPreference> getPreference(Long customerId) {
        return customerPreferenceRepository.findByCustomerId(customerId);
    }
}
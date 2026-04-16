package com.gms.backend.MyVehicle.service;

import com.gms.backend.MyVehicle.dto.MyVehicleDto;
import com.gms.backend.MyVehicle.entity.MyVehicleEntity;
import com.gms.backend.MyVehicle.repository.MyVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyVehicleService {

    private final MyVehicleRepository repo;


    private MyVehicleDto toDTO(MyVehicleEntity v) {
        MyVehicleDto dto = new MyVehicleDto();

        dto.setId(v.getId());
        dto.setCustomerId(v.getCustomerId());
        dto.setMake(v.getMake());
        dto.setModel(v.getModel());
        dto.setYear(v.getYear());
        dto.setFuelType(v.getFuelType());
        dto.setPlateNo(v.getPlateNo());
        dto.setIsPrimary(Boolean.TRUE.equals(v.getIsPrimary()));
        dto.setLastServiceDate(v.getLastServiceDate());
        dto.setNextDueDate(v.getNextDueDate());

        return dto;
    }


    private MyVehicleEntity toEntity(MyVehicleDto dto) {
        MyVehicleEntity v = new MyVehicleEntity();

        v.setId(dto.getId());
        v.setCustomerId(dto.getCustomerId());
        v.setMake(dto.getMake());
        v.setModel(dto.getModel());
        v.setYear(dto.getYear());
        v.setFuelType(dto.getFuelType());
        v.setPlateNo(dto.getPlateNo());
        v.setIsPrimary(Boolean.TRUE.equals(dto.getIsPrimary()));
        v.setLastServiceDate(dto.getLastServiceDate());
        v.setNextDueDate(dto.getNextDueDate());

        return v;
    }


    public List<MyVehicleDto> getVehicles(String query) {

        List<MyVehicleEntity> list;

        if (query != null && !query.isEmpty()) {
            list = repo.findByMakeContainingIgnoreCaseOrModelContainingIgnoreCase(query, query);
        } else {
            list = repo.findAll();
        }

        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }


    public MyVehicleDto addVehicle(MyVehicleDto dto) {

        MyVehicleEntity v = toEntity(dto);

        // first vehicle → primary
        if (repo.findByCustomerId(dto.getCustomerId()).isEmpty()) {
            v.setIsPrimary(true);
        }

        return toDTO(repo.save(v));
    }


    public void deleteVehicle(Long id) {

        MyVehicleEntity deleted = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        repo.deleteById(id);

        // if deleted vehicle was primary → assign another
        if (Boolean.TRUE.equals(deleted.getIsPrimary())) {
            List<MyVehicleEntity> list = repo.findByCustomerId(deleted.getCustomerId());

            if (!list.isEmpty()) {
                MyVehicleEntity first = list.get(0);
                first.setIsPrimary(true);
                repo.save(first);
            }
        }
    }


    public MyVehicleDto updateVehicle(Long id, MyVehicleDto dto) {

        MyVehicleEntity v = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        v.setCustomerId(dto.getCustomerId());
        v.setMake(dto.getMake());
        v.setModel(dto.getModel());
        v.setYear(dto.getYear());
        v.setFuelType(dto.getFuelType());
        v.setPlateNo(dto.getPlateNo());
        v.setIsPrimary(Boolean.TRUE.equals(dto.getIsPrimary()));
        v.setLastServiceDate(dto.getLastServiceDate());
        v.setNextDueDate(dto.getNextDueDate());

        return toDTO(repo.save(v));
    }


    public void setPrimary(Long id) {


        MyVehicleEntity selected = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));


        List<MyVehicleEntity> list = repo.findByCustomerId(selected.getCustomerId());


        for (MyVehicleEntity v : list) {
            v.setIsPrimary(v.getId().equals(id));
        }

        repo.saveAll(list);
    }
}
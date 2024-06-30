package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.dtos.AvailableParkingSpaceDto;
import com.babalola.smartparkingapplication.domain.enums.VehicleTypeEnum;

import java.util.List;
import java.util.Optional;

public interface AvailableParkingSpaceService {
    AvailableParkingSpaceDto save(AvailableParkingSpaceDto availableParkingSpaceDto);
    Optional<AvailableParkingSpaceDto> findById(Long id);
    List<AvailableParkingSpaceDto> findAll();
    AvailableParkingSpaceDto update(AvailableParkingSpaceDto availableParkingSpaceDto);
    void deleteById(Long id);
    List<AvailableParkingSpaceDto> findByOwnerId(Long ownerId);
    List<AvailableParkingSpaceDto> findByGarageId(Long garageId);
    List<AvailableParkingSpaceDto> findByVehicleType(VehicleTypeEnum vehicleType);
    List<AvailableParkingSpaceDto> findByLocation(double latitude, double longitude);
}

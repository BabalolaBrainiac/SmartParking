package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.dtos.AdminUserDto;
import com.babalola.smartparkingapplication.dtos.AvailableParkingSpaceDto;

import java.util.List;
import java.util.Optional;

public interface AvailableParkingSpaceService {
    AvailableParkingSpaceDto save(AvailableParkingSpaceDto availableParkingSpaceDto);
    Optional<AvailableParkingSpaceDto> findById(Long id);
    List<AvailableParkingSpaceDto> findAll();
    AvailableParkingSpaceDto update(AvailableParkingSpaceDto availableParkingSpaceDto);
    void deleteById(Long id);
}

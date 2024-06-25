package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.dtos.DriverDTO;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    DriverDTO save(DriverDTO driverDTO);
    Optional<DriverDTO> findById(Long id);
    List<DriverDTO> findAll();
    DriverDTO update(DriverDTO driverDTO);
    void deleteById(Long id);
}

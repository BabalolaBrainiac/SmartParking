package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.dtos.LocationDto;
import com.babalola.smartparkingapplication.dtos.ParkingGarageDto;

import java.util.List;
import java.util.Optional;

public interface ParkingGarageService {
    ParkingGarageDto save(ParkingGarageDto parkingGarageDto);
    Optional<ParkingGarageDto> findById(Long id);
    List<ParkingGarageDto> findAll();
    ParkingGarageDto update(ParkingGarageDto parkingGarageDto);
    void deleteById(Long id);
}

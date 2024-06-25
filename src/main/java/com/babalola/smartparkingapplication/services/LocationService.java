package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.dtos.AdminUserDto;
import com.babalola.smartparkingapplication.dtos.DriverDTO;
import com.babalola.smartparkingapplication.dtos.LocationDto;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    LocationDto save(LocationDto locationDto);
    Optional<LocationDto> findById(Long id);
    List<LocationDto> findAll();
    LocationDto update(LocationDto locationDto);
    void deleteById(Long id);
}

package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.Location;
import com.babalola.smartparkingapplication.dtos.AdminUserDto;
import com.babalola.smartparkingapplication.dtos.DriverDTO;
import com.babalola.smartparkingapplication.dtos.LocationDto;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    Location save(LocationDto locationDto);
    Optional<LocationDto> findById(Long id);
    List<LocationDto> findAll();
    LocationDto update(LocationDto locationDto);
    void deleteById(Long id);
    List<LocationDto> findWithinDistance(double longitude, double latitude, double distanceInMeters);


    List<LocationDto> findByLatitudeAndLongitude(double longitude, double latitude);
}

package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.ParkAddress;
import com.babalola.smartparkingapplication.dtos.LocationDto;
import com.babalola.smartparkingapplication.dtos.ParkAddressDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ParkingAddressService {

    Optional<ParkAddressDto> findById(Long id);
    List<ParkAddressDto> findAll();
    ParkAddressDto update(ParkAddressDto parkAddressDto);
    void deleteById(Long id);
    @Transactional
    ParkAddressDto save(ParkAddress parkAddressDto);
}

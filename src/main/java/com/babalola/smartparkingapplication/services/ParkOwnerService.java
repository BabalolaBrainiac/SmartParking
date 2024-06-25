package com.babalola.smartparkingapplication.services;
import com.babalola.smartparkingapplication.dtos.ParkOwnerDto;

import java.util.List;
import java.util.Optional;

public interface ParkOwnerService {

    ParkOwnerDto save(ParkOwnerDto parkOwnerDto);
    Optional<ParkOwnerDto> findById(Long id);
    List<ParkOwnerDto> findAll();
    ParkOwnerDto update(ParkOwnerDto parkOwnerDto);
    void deleteById(Long id);
}

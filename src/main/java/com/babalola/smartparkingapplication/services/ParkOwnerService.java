package com.babalola.smartparkingapplication.services;
import com.babalola.smartparkingapplication.dtos.ParkOwnerDto;
import com.babalola.smartparkingapplication.dtos.ParkingGarageDto;
import com.babalola.smartparkingapplication.dtos.ParkingGarageResponseDto;

import java.util.List;
import java.util.Optional;

public interface ParkOwnerService {

    ParkOwnerDto save(ParkOwnerDto parkOwnerDto);
    Optional<ParkOwnerDto> findById(Long id);
    List<ParkOwnerDto> findAll();
    ParkOwnerDto update(Long id, ParkOwnerDto parkOwnerDto);
    void deleteById(Long id);

    ParkOwnerDto getParkOwnerWithGarages(Long id);

    ParkingGarageResponseDto ownerAddNewParkingGarage(Long ownerId, ParkingGarageDto parkingGarageDto);

}

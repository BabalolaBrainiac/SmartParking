package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.entities.Location;
import com.babalola.smartparkingapplication.domain.entities.ParkAddress;
import com.babalola.smartparkingapplication.domain.entities.ParkingGarage;
import com.babalola.smartparkingapplication.dtos.LocationDto;
import com.babalola.smartparkingapplication.dtos.ParkAddressDto;
import com.babalola.smartparkingapplication.dtos.ParkingGarageDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingGarageRepository extends JpaRepository<ParkingGarage, Long> {
    Optional<ParkingGarage> findByAddressAndLocation(ParkAddress address, Location location);
}

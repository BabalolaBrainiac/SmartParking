package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.entities.AvailableParkingSpace;
import com.babalola.smartparkingapplication.domain.entities.Location;
import com.babalola.smartparkingapplication.domain.enums.VehicleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableParkingSpaceRepository extends JpaRepository<AvailableParkingSpace, Long> {
    List<AvailableParkingSpace> findByParkingGarageParkOwnerId(Long ownerId);
    List<AvailableParkingSpace> findByParkingGarageId(Long garageId);
    List<AvailableParkingSpace> findByVehicleType(VehicleTypeEnum vehicleType);
    List<AvailableParkingSpace> findByParkingGarage_Location(Location location);
}

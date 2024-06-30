package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}

package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.model.ParkingGarage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingGarageRepository extends JpaRepository<ParkingGarage, Long> {
}

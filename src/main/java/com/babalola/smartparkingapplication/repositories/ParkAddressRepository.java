package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.model.ParkAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkAddressRepository extends JpaRepository<ParkAddress, Long> {
}

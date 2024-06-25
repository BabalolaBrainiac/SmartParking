package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.entities.ParkAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkAddressRepository extends JpaRepository<ParkAddress, Long> {
    Optional<ParkAddress> findByStreetAndCityAndStateAndZipCode(String street, String city, String state, String s);
}

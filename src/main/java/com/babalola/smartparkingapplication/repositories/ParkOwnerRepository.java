package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.entities.ParkOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkOwnerRepository extends JpaRepository<ParkOwner, Long> {
    Optional<ParkOwner> findByEmail(String email);

    Optional<ParkOwner> findByPhoneNumber(String s);
}

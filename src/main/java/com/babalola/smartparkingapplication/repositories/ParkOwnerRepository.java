package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.model.ParkOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkOwnerRepository extends JpaRepository<ParkOwner, Long> {
}

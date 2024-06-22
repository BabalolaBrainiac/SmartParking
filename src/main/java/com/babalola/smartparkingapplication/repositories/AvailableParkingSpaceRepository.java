package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.model.AvailableParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableParkingSpaceRepository extends JpaRepository<AvailableParkingSpace, Long> {
}

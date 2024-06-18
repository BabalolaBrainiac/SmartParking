package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}

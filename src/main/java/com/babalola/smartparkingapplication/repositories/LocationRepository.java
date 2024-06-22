package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}

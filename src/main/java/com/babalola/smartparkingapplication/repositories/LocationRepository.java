package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByLatitudeAndLongitude(double latitude, double longitude);

    @Query(value = "SELECT * FROM locations WHERE ST_DWithin(location, ST_SetSRID(ST_MakePoint(?1, ?2), 4326), ?3) ORDER BY ST_Distance(location, ST_SetSRID(ST_MakePoint(?1, ?2), 4326))", nativeQuery = true)
    List<Location> findWithinDistance(double longitude, double latitude, double distanceInMeters);
}

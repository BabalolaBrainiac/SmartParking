package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.Location;
import com.babalola.smartparkingapplication.domain.mappers.LocationMapper;
import com.babalola.smartparkingapplication.dtos.LocationDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.repositories.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }
    /**
     * @param locationDto
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public LocationDto save(LocationDto locationDto) {
        Optional<Location> existingLocation = locationRepository.findByLatitudeAndLongitude(
                locationDto.latitude(), locationDto.longitude());

        if (existingLocation.isPresent()) {
            throw new ResourceExistsException("Location already exists");
        }

        Location location = locationMapper.locationDTOToLocation(locationDto);
        location = locationRepository.save(location);

        return locationMapper.locationToLocationDTO(location);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<LocationDto> findById(Long id) {
        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public List<LocationDto> findAll() {
        return null;
    }

    /**
     * @param locationDto
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public LocationDto update(LocationDto locationDto) {
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {

    }
}

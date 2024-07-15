package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.Location;
import com.babalola.smartparkingapplication.domain.mappers.LocationMapper;
import com.babalola.smartparkingapplication.dtos.LocationDto;
import com.babalola.smartparkingapplication.repositories.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final GeometryFactory geometryFactory = new GeometryFactory();
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
    public Location save(LocationDto locationDto) {
        Optional<Location> existingLocation = locationRepository.findByLatitudeAndLongitude(
                locationDto.latitude(), locationDto.longitude());

            if (existingLocation.isPresent()) {
                return existingLocation.get();
            }

        Location location = new Location();
        location.setLatitude(locationDto.latitude());
        location.setLongitude(locationDto.longitude());
        location.setDescription(locationDto.description());

        Point point = geometryFactory.createPoint(new Coordinate(locationDto.longitude(), locationDto.latitude()));
        location.setLocation(point);


        System.out.println("hereesaveee");
        return locationRepository.save(location);
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
        locationRepository.deleteById(id);
    }


    @Override
    public List<LocationDto> findWithinDistance(double longitude, double latitude, double distanceInMeters) {
        var items = locationRepository.findWithinDistance(longitude, latitude, distanceInMeters);

        if(items.isEmpty()) {
            throw new ResourceNotFoundException("no spaces for provided address");
        }
        return items.stream()
                .map(locationMapper::locationToLocationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationDto> findByLatitudeAndLongitude(double longitude, double latitude) {
        var items = locationRepository.findWithinDistance(longitude, latitude, 50);

        if(items.isEmpty()) {
            throw new ResourceNotFoundException("no spaces for provided address");
        }
        return items.stream()
                .map(locationMapper::locationToLocationDTO)
                .collect(Collectors.toList());
    }
}

package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.AvailableParkingSpace;
import com.babalola.smartparkingapplication.domain.entities.Location;
import com.babalola.smartparkingapplication.domain.enums.VehicleTypeEnum;
import com.babalola.smartparkingapplication.domain.mappers.AvailableParkingSpaceMapper;
import com.babalola.smartparkingapplication.dtos.AvailableParkingSpaceDto;
import com.babalola.smartparkingapplication.repositories.AvailableParkingSpaceRepository;
import com.babalola.smartparkingapplication.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableParkingSpaceServiceImpl implements AvailableParkingSpaceService {

    private final AvailableParkingSpaceRepository availableParkingSpaceRepository;
    private final LocationRepository locationRepository;
    private final AvailableParkingSpaceMapper availableParkingSpaceMapper;

    @Override
    @Transactional
    public AvailableParkingSpaceDto save(AvailableParkingSpaceDto availableParkingSpaceDto) {
        AvailableParkingSpace availableParkingSpace = availableParkingSpaceMapper.toEntity(availableParkingSpaceDto);
        availableParkingSpace = availableParkingSpaceRepository.save(availableParkingSpace);
        return availableParkingSpaceMapper.toDto(availableParkingSpace);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AvailableParkingSpaceDto> findById(Long id) {
        return availableParkingSpaceRepository.findById(id)
                .map(availableParkingSpaceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AvailableParkingSpaceDto> findAll() {
        return availableParkingSpaceRepository.findAll().stream()
                .map(availableParkingSpaceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AvailableParkingSpaceDto update(AvailableParkingSpaceDto availableParkingSpaceDto) {
        AvailableParkingSpace availableParkingSpace = availableParkingSpaceMapper.toEntity(availableParkingSpaceDto);
        availableParkingSpace = availableParkingSpaceRepository.save(availableParkingSpace);
        return availableParkingSpaceMapper.toDto(availableParkingSpace);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        availableParkingSpaceRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<AvailableParkingSpaceDto> findByOwnerId(Long ownerId) {
        List<AvailableParkingSpace> spaces = availableParkingSpaceRepository.findByParkingGarageParkOwnerId(ownerId);
        return availableParkingSpaceMapper.toDtos(spaces);
    }

    @Transactional(readOnly = true)
    public List<AvailableParkingSpaceDto> findByGarageId(Long garageId) {
        List<AvailableParkingSpaceDto> spaces = availableParkingSpaceRepository.findByParkingGarageId(garageId).stream()
                .map(availableParkingSpaceMapper::toDto)
                .collect(Collectors.toList());

        if(spaces.isEmpty()) {
            throw new ResourceNotFoundException("No available spaces available for given resource");
        }

        return spaces;
    }

    @Transactional(readOnly = true)
    public List<AvailableParkingSpaceDto> findByVehicleType(VehicleTypeEnum vehicleType) {
        List<AvailableParkingSpaceDto> spaces = availableParkingSpaceRepository.findByVehicleType(vehicleType).stream()
                .map(availableParkingSpaceMapper::toDto)
                .collect(Collectors.toList());

        if(spaces.isEmpty()) {
            throw new ResourceNotFoundException("No available spaces available for given resource");
        }

        return spaces;
    }

    @Transactional(readOnly = true)
    public List<AvailableParkingSpaceDto> findByLocation(double latitude, double longitude) {
        List<Location> locations = locationRepository.findWithinDistance(longitude, latitude, 10);

        if (locations.isEmpty()) {
            throw new ResourceNotFoundException("No locations found within the provided distance");
        }

        List<AvailableParkingSpaceDto> parkingSpaceDtos = locations.stream()
                .flatMap(location -> availableParkingSpaceRepository.findByParkingGarageLocation(location).stream())
                .map(availableParkingSpaceMapper::toDto)
                .collect(Collectors.toList());

        if (parkingSpaceDtos.isEmpty()) {
            throw new ResourceNotFoundException("No parking spaces found for the provided locations");
        }

        return parkingSpaceDtos;
    }
}

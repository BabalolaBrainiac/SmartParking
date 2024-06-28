package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.Location;
import com.babalola.smartparkingapplication.domain.entities.ParkAddress;
import com.babalola.smartparkingapplication.domain.entities.ParkingGarage;
import com.babalola.smartparkingapplication.domain.mappers.ParkingGarageMapper;
import com.babalola.smartparkingapplication.dtos.ParkingGarageDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.repositories.LocationRepository;
import com.babalola.smartparkingapplication.repositories.ParkingGarageRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
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
public class ParkingGarageServiceImpl implements ParkingGarageService {

    private final ParkingGarageRepository parkingGarageRepository;
    private final ParkingGarageMapper parkingGarageMapper;
    private final LocationRepository locationRepository;

    @Autowired
    public ParkingGarageServiceImpl(ParkingGarageRepository parkingGarageRepository, LocationRepository locationRepository, ParkingGarageMapper parkingGarageMapper) {
        this.parkingGarageRepository = parkingGarageRepository;
        this.locationRepository = locationRepository;
        this.parkingGarageMapper = parkingGarageMapper;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ParkingGarageDto save(ParkingGarageDto parkingGarageDto) {
        ParkingGarage parkingGarage = parkingGarageMapper.parkingGarageDTOToParkingGarage(parkingGarageDto);

        Location location;
        if (parkingGarageDto.location().id() != null) {
            location = locationRepository.findById(parkingGarageDto.location().id())
                    .orElseGet(() -> {
                        Location newLocation = new Location();
                        newLocation.setLatitude(parkingGarageDto.location().latitude());
                        newLocation.setLongitude(parkingGarageDto.location().longitude());
                        newLocation.setDescription(parkingGarageDto.location().description());
                        return newLocation;
                    });
        } else {
            Location newLocation = new Location();
            newLocation.setLatitude(parkingGarageDto.location().latitude());
            newLocation.setLongitude(parkingGarageDto.location().longitude());
            newLocation.setDescription(parkingGarageDto.location().description());
            location = newLocation;
        }

        ParkAddress parkAddress = new ParkAddress();
        parkAddress.setCity(parkingGarageDto.address().city());
        parkAddress.setState(parkingGarageDto.address().state());
        parkAddress.setStreet(parkingGarageDto.address().street());
        parkAddress.setZipCode(parkingGarageDto.address().zipCode());
        parkAddress.setLocation(location);

        parkingGarage.setAddress(parkAddress);

        parkingGarage = parkingGarageRepository.save(parkingGarage);

        return parkingGarageMapper.parkingGarageToParkingGarageDTO(parkingGarage);
    }


    @Override
    public Optional<ParkingGarageDto> findById(Long id) {
        return parkingGarageRepository.findById(id).map(parkingGarageMapper::parkingGarageToParkingGarageDTO);
    }

    @Override
    public List<ParkingGarageDto> findAll() {
        return parkingGarageRepository.findAll().stream()
                .map(parkingGarageMapper::parkingGarageToParkingGarageDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ParkingGarageDto update(ParkingGarageDto parkingGarageDto) {
        if (parkingGarageRepository.existsById(parkingGarageDto.id())) {
            ParkingGarage parkingGarage = parkingGarageMapper.parkingGarageDTOToParkingGarage(parkingGarageDto);
            parkingGarage = parkingGarageRepository.save(parkingGarage);
            return parkingGarageMapper.parkingGarageToParkingGarageDTO(parkingGarage);
        } else {
            throw new RuntimeException("Parking Garage not found");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        parkingGarageRepository.deleteById(id);
    }


}

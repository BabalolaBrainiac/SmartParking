package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.*;
import com.babalola.smartparkingapplication.domain.mappers.*;
import com.babalola.smartparkingapplication.dtos.*;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.hibernate.Hibernate;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ParkOwnerServiceImpl implements ParkOwnerService {

    private final ParkOwnerRepository parkOwnerRepository;
    private final ParkingGarageRepository parkingGarageRepository;
    private final ParkingAddressService parkingAddressService;
    private final ParkOwnerMapper parkOwnerMapper;
    private final AvailableParkingSpaceRepository availableParkingSpaceRepository;

    private final LocationService locationService;


    public ParkOwnerServiceImpl(ParkOwnerRepository parkOwnerRepository, ParkingAddressService parkingAddressService, ParkingGarageRepository parkingGarageRepository, ParkOwnerMapper parkOwnerMapper, AvailableParkingSpaceRepository availableParkingSpaceRepository, LocationService locationService1) {
        this.parkOwnerRepository = parkOwnerRepository;
        this.parkingGarageRepository = parkingGarageRepository;
        this.parkingAddressService = parkingAddressService;
        this.parkOwnerMapper = parkOwnerMapper;
        this.availableParkingSpaceRepository = availableParkingSpaceRepository;
        this.locationService = locationService1;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ParkOwnerDto save(ParkOwnerDto parkOwnerDto) {
        Optional<ParkOwner> existingUserByEmail = parkOwnerRepository.findByEmail(parkOwnerDto.email());
        Optional<ParkOwner> existingUserByPhoneNumber = parkOwnerRepository.findByPhoneNumber(parkOwnerDto.phoneNumber());

        if (existingUserByEmail.isPresent() || existingUserByPhoneNumber.isPresent()) {
            throw new ResourceExistsException("User with the same email or phone number already exists");
        }

        ParkOwner parkOwner = parkOwnerMapper.parkOwnerDtoToParkOwner(parkOwnerDto);
        parkOwnerRepository.save(parkOwner);
        return parkOwnerMapper.parkOwnerToParkOwnerDTO(parkOwner);
    }

    @Override
    public Optional<ParkOwnerDto> findById(Long id) {
        return parkOwnerRepository.findById(id).map(parkOwnerMapper::parkOwnerToParkOwnerDTO);
    }

    @Override
    public List<ParkOwnerDto> findAll() {
        return parkOwnerRepository.findAll().stream()
                .map(parkOwnerMapper::parkOwnerToParkOwnerDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ParkOwnerDto update(Long id, ParkOwnerDto parkOwnerDto) {
        Optional<ParkOwner> optionalParkOwner = parkOwnerRepository.findById(id);

        if (optionalParkOwner.isEmpty()) {
            throw new RuntimeException("ParkOwner not found");
        }

        ParkOwner parkOwner = optionalParkOwner.get();

        if (parkOwnerDto.firstName() != null) {
            parkOwner.setFirstName(parkOwnerDto.firstName());
        }
        if (parkOwnerDto.lastName() != null) {
            parkOwner.setLastName(parkOwnerDto.lastName());
        }
        if (parkOwnerDto.email() != null) {
            parkOwner.setEmail(parkOwnerDto.email());
        }
        if (parkOwnerDto.phoneNumber() != null) {
            parkOwner.setPhoneNumber(parkOwnerDto.phoneNumber());
        }
        if (parkOwnerDto.isDeleted() != null) {
            parkOwner.setIsDeleted(parkOwnerDto.isDeleted());
        }

        parkOwnerRepository.save(parkOwner);

        return ParkOwnerMapper.INSTANCE.parkOwnerToParkOwnerDTO(parkOwner);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        parkOwnerRepository.deleteById(id);
    }

    @Transactional
    public ParkOwnerDto getParkOwnerWithGarages(Long ownerId) {
        ParkOwner parkOwner = parkOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("ParkOwner not found"));

        Hibernate.initialize(parkOwner.getParkingGarages());

        return ParkOwnerMapper.INSTANCE.parkOwnerToParkOwnerDTO(parkOwner);
    }

    @Transactional
    public ParkingGarageResponseDto ownerAddNewParkingGarage(Long ownerId, ParkingGarageDto parkingGarageDto) {
        ParkOwner parkOwner = parkOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Park owner with id " + parkingGarageDto.id() + "not found"));


        Location location = this.locationService.save(parkingGarageDto.location());


        ParkAddressDto addressDto = parkingGarageDto.address();
        ParkAddress parkAddress = new ParkAddress();
        parkAddress.setCity(addressDto.city());
        parkAddress.setState(addressDto.state());
        parkAddress.setStreet(addressDto.street());
        parkAddress.setZipCode(addressDto.zipCode());
        parkAddress.setLocation(location);
        parkAddress = this.parkingAddressService.save(parkAddress);


        ParkingGarage parkingGarage = new ParkingGarage();
        parkingGarage.setLocation(location);
        parkingGarage.setAddress(parkAddress);
        parkingGarage.setParkOwner(parkOwner);
        var newGarage = parkingGarageRepository.save(parkingGarage);

        if (!parkingGarageDto.availableParkingSpaces().isEmpty()) {
            List<AvailableParkingSpace> parkingSpaces = parkingGarageDto.availableParkingSpaces().stream()
                    .map(spaceDto -> {
                        AvailableParkingSpace space = AvailableParkingSpaceMapper.INSTANCE.toEntity(spaceDto);
                        space.setParkingGarage(newGarage);
                        return space;
                    })
                    .collect(Collectors.toList());

            availableParkingSpaceRepository.saveAll(parkingSpaces);
            parkingGarage.setAvailableParkingSpaces(parkingSpaces);
        }
        parkingGarageRepository.save(newGarage);


        if (parkingGarage.getVehicles() == null) {
            parkingGarage.setVehicles(new ArrayList<>());
        }

        return ParkingGarageMapper.INSTANCE.parkingGarageToParkingGarageResponseDto(newGarage);
    }

}

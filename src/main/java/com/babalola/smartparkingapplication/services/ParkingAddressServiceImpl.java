package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.ParkAddress;
import com.babalola.smartparkingapplication.domain.mappers.ParkAddressMapper;
import com.babalola.smartparkingapplication.dtos.ParkAddressDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.repositories.ParkAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ParkingAddressServiceImpl implements ParkingAddressService {

    private final ParkAddressRepository parkAddressRepository;
    private final ParkAddressMapper parkAddressMapper;

    @Autowired
    public ParkingAddressServiceImpl(ParkAddressRepository parkAddressRepository, ParkAddressMapper parkAddressMapper) {
        this.parkAddressRepository = parkAddressRepository;
        this.parkAddressMapper = parkAddressMapper;
    }

    @Override
    @Transactional
    public ParkAddressDto save(ParkAddressDto parkAddressDto) {
        Optional<ParkAddress> existingAddress = parkAddressRepository.findByStreetAndCityAndStateAndZipCode(
                parkAddressDto.street(), parkAddressDto.city(), parkAddressDto.state(), parkAddressDto.zipCode());

        if (existingAddress.isPresent()) {
            throw new ResourceExistsException("Address already exists");
        }

        ParkAddress parkAddress = parkAddressMapper.parkAddressDTOToParkAddress(parkAddressDto);
        parkAddress = parkAddressRepository.save(parkAddress);

        return parkAddressMapper.parkAddressToParkAddressDTO(parkAddress);
    }


    @Override
    public Optional<ParkAddressDto> findById(Long id) {
        return parkAddressRepository.findById(id).map(parkAddressMapper::parkAddressToParkAddressDTO);
    }


    @Override
    public List<ParkAddressDto> findAll() {
        return parkAddressRepository.findAll().stream()
                .map(parkAddressMapper::parkAddressToParkAddressDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public ParkAddressDto update(ParkAddressDto parkAddressDto) {
        if (parkAddressRepository.existsById(parkAddressDto.id())) {
            ParkAddress parkAddress = parkAddressMapper.parkAddressDTOToParkAddress(parkAddressDto);
            parkAddress = parkAddressRepository.save(parkAddress);
            return parkAddressMapper.parkAddressToParkAddressDTO(parkAddress);
        } else {
            throw new RuntimeException("ParkAddress not found");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        parkAddressRepository.deleteById(id);
    }
}

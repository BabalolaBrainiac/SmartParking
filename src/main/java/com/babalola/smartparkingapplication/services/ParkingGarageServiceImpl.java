package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.ParkingGarage;
import com.babalola.smartparkingapplication.domain.mappers.ParkingGarageMapper;
import com.babalola.smartparkingapplication.dtos.ParkingGarageDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.repositories.ParkingGarageRepository;
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
public class ParkingGarageServiceImpl implements ParkingGarageService {

    private final ParkingGarageRepository parkingGarageRepository;
    private final ParkingGarageMapper parkingGarageMapper;

    @Autowired
    public ParkingGarageServiceImpl(ParkingGarageRepository parkingGarageRepository, ParkingGarageMapper parkingGarageMapper) {
        this.parkingGarageRepository = parkingGarageRepository;
        this.parkingGarageMapper = parkingGarageMapper;
    }

    @Override
    @Transactional
    public ParkingGarageDto save(ParkingGarageDto parkingGarageDto) {

        ParkingGarage parkingGarage = parkingGarageMapper.parkingGarageDTOToParkingGarage(parkingGarageDto);
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
    @Transactional
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

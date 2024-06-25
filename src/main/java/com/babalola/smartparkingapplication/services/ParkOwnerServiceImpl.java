package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.ParkOwner;
import com.babalola.smartparkingapplication.domain.mappers.ParkOwnerMapper;
import com.babalola.smartparkingapplication.dtos.ParkOwnerDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.repositories.ParkOwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ParkOwnerServiceImpl implements ParkOwnerService {

    private final ParkOwnerRepository parkOwnerRepository;
    private final ParkOwnerMapper parkOwnerMapper;

    public ParkOwnerServiceImpl(ParkOwnerRepository parkOwnerRepository, ParkOwnerMapper parkOwnerMapper) {
        this.parkOwnerRepository = parkOwnerRepository;
        this.parkOwnerMapper = parkOwnerMapper;
    }

    @Override
    @Transactional
    public ParkOwnerDto save(ParkOwnerDto parkOwnerDto) {
        Optional<ParkOwner> existingUserByEmail = parkOwnerRepository.findByEmail(parkOwnerDto.email());
        Optional<ParkOwner> existingUserByPhoneNumber = parkOwnerRepository.findByPhoneNumber(parkOwnerDto.phoneNumber());

        if (existingUserByEmail.isPresent() || existingUserByPhoneNumber.isPresent()) {
            throw new ResourceExistsException("User with the same email or phone number already exists");
        }

        ParkOwner parkOwner = parkOwnerMapper.parkOwnerDTOToParkOwner(parkOwnerDto);
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
    @Transactional
    public ParkOwnerDto update(ParkOwnerDto parkOwnerDto) {
        if (parkOwnerRepository.existsById(parkOwnerDto.id())) {
            ParkOwner parkOwner = parkOwnerMapper.parkOwnerDTOToParkOwner(parkOwnerDto);
            parkOwnerRepository.save(parkOwner);
            return parkOwnerMapper.parkOwnerToParkOwnerDTO(parkOwner);
        } else {
            throw new RuntimeException("ParkOwner not found");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        parkOwnerRepository.deleteById(id);
    }
}

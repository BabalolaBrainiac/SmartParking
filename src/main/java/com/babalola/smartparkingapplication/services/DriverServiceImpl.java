package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.AdminUser;
import com.babalola.smartparkingapplication.domain.entities.Driver;
import com.babalola.smartparkingapplication.domain.mappers.DriverMapper;
import com.babalola.smartparkingapplication.dtos.DriverDTO;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.repositories.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@Transactional(readOnly = true)
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    private final DriverMapper driverMapper;

    public DriverServiceImpl(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    /**
     * @param driverDTO
     * @return
     */
    @Override
    public DriverDTO save(DriverDTO driverDTO) {
        Optional<Driver> existingUserByEmail = driverRepository.findByEmail(driverDTO.email());
        Optional<Driver> existingUserByPhoneNumber = driverRepository.findByPhoneNumber(driverDTO.phoneNumber());

        if (existingUserByEmail.isPresent() || existingUserByPhoneNumber.isPresent()) {
            throw new ResourceExistsException("User with the same email or phone number already exists");
        }

        Driver driver = driverMapper.driverDTOToDriver(driverDTO);

      driverRepository.save(driver);

        return driverMapper.driverToDriverDTO(driver);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<DriverDTO> findById(Long id) {
        return driverRepository.findById(id).map(driverMapper::driverToDriverDTO);
    }

    /**
     * @return
     */
    @Override
    public List<DriverDTO> findAll() {
        return driverRepository.findAll().stream()
                .map(driverMapper::driverToDriverDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param driverDTO
     * @return DriverDto
     */
    @Override
    public DriverDTO update(DriverDTO driverDTO) {
        if (driverRepository.existsById(driverDTO.id())) {
            Driver driver = driverMapper.driverDTOToDriver(driverDTO);
           driverRepository.save(driver);
            return driverMapper.driverToDriverDTO(driver);
        } else {
            throw new RuntimeException("Driver not found");
        }
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {

        driverRepository.deleteById(id);

    }
}


package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.AdminUser;
import com.babalola.smartparkingapplication.domain.mappers.AdminMapper;
import com.babalola.smartparkingapplication.dtos.AdminUserDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.repositories.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    private ObjectMapper objectMapper;

    @Autowired
    public AdminUserServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AdminUserDto save(AdminUserDto adminUserDto) {
        Optional<AdminUser> existingUserByEmail = adminRepository.findByEmail(adminUserDto.email());
        Optional<AdminUser> existingUserByPhoneNumber = adminRepository.findByPhoneNumber(adminUserDto. phoneNumber());

        if (existingUserByEmail.isPresent() || existingUserByPhoneNumber.isPresent()) {
            throw new ResourceExistsException("User with the same email or phone number already exists");
        }

        AdminUser adminUser = adminMapper.adminDTOToAdmin(adminUserDto);

        adminUser = adminRepository.save(adminUser);

        return adminMapper.adminToAdminDTO(adminUser);
    }

    @Override
    public Optional<AdminUserDto> findById(Long id) {
        return adminRepository.findById(id).map(adminMapper::adminToAdminDTO);
    }

    @Override
    public List<AdminUserDto> findAll() {
        return adminRepository.findAll().stream()
                .map(adminMapper::adminToAdminDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AdminUserDto update(AdminUserDto adminUserDto) {
        if (adminRepository.existsById(adminUserDto.id())) {
            AdminUser adminUser = adminMapper.adminDTOToAdmin(adminUserDto);
            adminUser = adminRepository.save(adminUser);
            return adminMapper.adminToAdminDTO(adminUser);
        } else {
            throw new RuntimeException("AdminUser not found");
        }
    }

    @Override
    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }
}

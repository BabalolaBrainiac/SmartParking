package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.mappers.AdminMapper;
import com.babalola.smartparkingapplication.domain.model.AdminUser;
import com.babalola.smartparkingapplication.dtos.AdminUserDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Autowired
    public AdminUserServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminUserDto save(AdminUserDto adminUserDto) {
        // Check if a user with the same email or phone number already exists
        Optional<AdminUser> existingUserByEmail = adminRepository.findByEmail(adminUserDto.email());
        Optional<AdminUser> existingUserByPhoneNumber = adminRepository.findByPhoneNumber(adminUserDto.phoneNumber());

        if (existingUserByEmail.isPresent() || existingUserByPhoneNumber.isPresent()) {
            throw new ResourceExistsException("User with the same email or phone number already exists");
        }

        // Convert DTO to entity
        AdminUser adminUser = adminMapper.adminDTOToAdmin(adminUserDto);

        // Save the user
        adminUser = adminRepository.save(adminUser);

        // Convert entity back to DTO and return
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

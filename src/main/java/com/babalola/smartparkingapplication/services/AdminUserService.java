package com.babalola.smartparkingapplication.services;
import com.babalola.smartparkingapplication.dtos.AdminUserDto;

import java.util.List;
import java.util.Optional;

public interface AdminUserService {
    AdminUserDto save(AdminUserDto adminUserDTO);
    Optional<AdminUserDto> findById(Long id);
    List<AdminUserDto> findAll();
    AdminUserDto update(AdminUserDto adminUserDTO);
    void deleteById(Long id);
}

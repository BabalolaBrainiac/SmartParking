package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.entities.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findByEmail(String email);
    Optional<AdminUser> findByPhoneNumber(String phoneNumber);
}

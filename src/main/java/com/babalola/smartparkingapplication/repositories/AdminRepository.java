package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminUser, Long> {
}

package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<AdminUser, Long> {
}

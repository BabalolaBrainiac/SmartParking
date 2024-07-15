package com.babalola.smartparkingapplication.repositories;


import com.babalola.smartparkingapplication.domain.entities.UserAuthDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthenticationDetailsRepository extends JpaRepository<UserAuthDetails, Long> {
    UserAuthDetails findByUsername(String username);
}

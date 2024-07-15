package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findByEmailAndPassword(String email, String password);
}

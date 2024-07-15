package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.User;
import com.babalola.smartparkingapplication.domain.entities.Driver;
import com.babalola.smartparkingapplication.domain.entities.ParkOwner;
import com.babalola.smartparkingapplication.domain.entities.AdminUser;
import com.babalola.smartparkingapplication.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto createUser(User user);

    User createDriver(Driver driver);

    User createParkOwner(ParkOwner parkOwner);

    User createAdminUser(AdminUser adminUser);

    User findUserByEmail(String email);

    List<User> findUsersByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findUserByEmailAndPassword(String email, String password);
}

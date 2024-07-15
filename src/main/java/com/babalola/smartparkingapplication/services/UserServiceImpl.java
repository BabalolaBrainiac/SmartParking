package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.entities.*;
import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import com.babalola.smartparkingapplication.domain.mappers.UserMapper;
import com.babalola.smartparkingapplication.dtos.UserDto;
import com.babalola.smartparkingapplication.repositories.UserRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDto createUser(User user) {
        User newUser = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDto(newUser);
    }

    @Override
    @Transactional
    public User createDriver(Driver driver) {
        User user = new User();
        user.setFirstName(driver.getFirstName());
        user.setLastName(driver.getLastName());
        user.setEmail(driver.getEmail());
        user.setPhoneNumber(driver.getPhoneNumber());
        user.setUserType(UserTypeEnum.DRIVER);
        user.setDriver(driver);

        driver.setUser(user);

        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User createParkOwner(ParkOwner parkOwner) {

        User user = new User();
        user.setFirstName(parkOwner.getFirstName());
        user.setLastName(parkOwner.getLastName());
        user.setEmail(parkOwner.getEmail());
        user.setPhoneNumber(parkOwner.getPhoneNumber());
        user.setParkOwner(parkOwner);
        user.setUserType(UserTypeEnum.PARK_OWNER);
        user.setUsername(parkOwner.getUsername());
        user.setPassword(parkOwner.getPassword());

        parkOwner.setUser(user);

        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User createAdminUser(AdminUser adminUser) {
        User user = new User();
        user.setFirstName(adminUser.getFirstName());
        user.setLastName(adminUser.getLastName());
        user.setEmail(adminUser.getEmail());
        user.setPhoneNumber(adminUser.getPhoneNumber());
        user.setUserType(UserTypeEnum.ADMIN);
        user.setAdminUser(adminUser);
        user.setUsername(adminUser.getUsername());
        user.setPassword(adminUser.getPassword());

        adminUser.setUser(user);

        userRepository.save(user);
        return user;

    }

    @Override
    public User findUserByEmail(String email) {
        var user = userRepository.findByEmail(email);

        if(user.isEmpty()) {
            throw new ResourceNotFoundException("User does not exist");
        }

        return user.get();

    }

    @Override
    public List<User> findUsersByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}

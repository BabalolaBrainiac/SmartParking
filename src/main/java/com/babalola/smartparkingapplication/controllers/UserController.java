package com.babalola.smartparkingapplication.controllers;

import com.babalola.smartparkingapplication.constants.ApplicationUrlMapping;
import com.babalola.smartparkingapplication.domain.entities.AdminUser;
import com.babalola.smartparkingapplication.domain.entities.Driver;
import com.babalola.smartparkingapplication.domain.entities.ParkOwner;
import com.babalola.smartparkingapplication.domain.entities.User;
import com.babalola.smartparkingapplication.domain.mappers.ParkOwnerMapper;
import com.babalola.smartparkingapplication.dtos.ParkOwnerDto;
import com.babalola.smartparkingapplication.dtos.UserDto;
import com.babalola.smartparkingapplication.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.babalola.smartparkingapplication.controllers.UserController.UsersAPI;

@RestController
@RequestMapping(UsersAPI)
public class UserController {
    public static final String UsersAPI = ApplicationUrlMapping.USERS_API;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin")
    @Operation(summary = "Create a new admin user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created admin user", content = @Content(schema = @Schema(implementation = AdminUser.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<User> createAdminUser(@RequestBody AdminUser adminUser) {
        return ResponseEntity.ok(userService.createAdminUser(adminUser));
    }

    @PostMapping("/driver")
    @Operation(summary = "Create a new driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created driver", content = @Content(schema = @Schema(implementation = Driver.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<User> createDriver(@RequestBody Driver driver) {
        return ResponseEntity.ok(userService.createDriver(driver));
    }

    @PostMapping("/park-owner")
    @Operation(summary = "Create a new park owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created park owner", content = @Content(schema = @Schema(implementation = ParkOwner.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<User> createParkOwner(@RequestBody ParkOwnerDto parkOwner) {

        var owner = ParkOwnerMapper.INSTANCE.parkOwnerDtoToParkOwner(parkOwner);
        return ResponseEntity.ok(userService.createParkOwner(owner));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Find user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found user", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @GetMapping("/name")
    @Operation(summary = "Find users by first name and last name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found users", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Users not found")
    })
    public ResponseEntity<List<User>> getUsersByFirstNameAndLastName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        return ResponseEntity.ok(userService.findUsersByFirstNameAndLastName(firstName, lastName));
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user by email and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated user", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "401", description = "Invalid email or password")
    })
    public ResponseEntity<Optional<User>> getUserByEmailAndPassword(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.findUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    static class LoginRequest {
        @Schema(description = "Email of the user", required = true)
        private String email;

        @Schema(description = "Password of the user", required = true)
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

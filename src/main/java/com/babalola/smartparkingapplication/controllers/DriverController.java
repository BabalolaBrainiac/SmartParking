package com.babalola.smartparkingapplication.controllers;

import com.babalola.smartparkingapplication.constants.ApplicationUrlMapping;
import com.babalola.smartparkingapplication.dtos.DriverDTO;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.services.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.babalola.smartparkingapplication.controllers.DriverController.DriverBaseUrl;

@RestController
@RequestMapping(DriverBaseUrl)
public class DriverController {
    public static final String DriverBaseUrl = ApplicationUrlMapping.DRIVER_API;

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @Operation(summary = "Create a new Driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Driver created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Driver already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createDriver(@RequestBody DriverDTO driverDTO) {
        try {
            DriverDTO savedDriver = driverService.save(driverDTO);
            return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
        } catch (ResourceExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get a Driver by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver found"),
            @ApiResponse(responseCode = "404", description = "Driver not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> getDriverById(@PathVariable Long id) {
        return driverService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all Drivers")
    @ApiResponse(responseCode = "200", description = "List of Drivers")
    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        List<DriverDTO> drivers = driverService.findAll();
        return ResponseEntity.ok(drivers);
    }

    @Operation(summary = "Update a Driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver updated successfully"),
            @ApiResponse(responseCode = "404", description = "Driver not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDriver(@PathVariable Long id, @RequestBody DriverDTO driverDTO) {
        try {
            DriverDTO updatedDriver = driverService.update(driverDTO);
            return ResponseEntity.ok(updatedDriver);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Delete a Driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Driver deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Driver not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        try {
            driverService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

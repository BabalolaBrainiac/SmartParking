package com.babalola.smartparkingapplication.controllers;

import com.babalola.smartparkingapplication.constants.ApplicationUrlMapping;
import com.babalola.smartparkingapplication.domain.entities.ParkAddress;
import com.babalola.smartparkingapplication.domain.mappers.ParkAddressMapper;
import com.babalola.smartparkingapplication.dtos.ParkAddressDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.services.ParkingAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ParkAddressController.ParkAddressBaseUrl)
public class ParkAddressController {

    public static final String ParkAddressBaseUrl = ApplicationUrlMapping.PARKING_ADDRESS_API;

    private final ParkingAddressService parkAddressService;

    @Autowired
    public ParkAddressController(ParkingAddressService parkAddressService) {
        this.parkAddressService = parkAddressService;
    }

    @Operation(summary = "Create a new Park Address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Park Address created successfully"),
            @ApiResponse(responseCode = "409", description = "Park Address already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createParkAddress(@RequestBody ParkAddressDto parkAddressDto) {
        try {
            ParkAddress savedParkAddress = parkAddressService.save(ParkAddressMapper.INSTANCE.parkAddressDTOToParkAddress(parkAddressDto));
            return new ResponseEntity<>(ParkAddressMapper.INSTANCE.parkAddressToParkAddressDTO(savedParkAddress), HttpStatus.CREATED);
        } catch (ResourceExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get a Park Address by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Park Address found"),
            @ApiResponse(responseCode = "404", description = "Park Address not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ParkAddressDto> getParkAddressById(@PathVariable Long id) {
        return parkAddressService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all Park Addresses")
    @ApiResponse(responseCode = "200", description = "List of Park Addresses")
    @GetMapping
    public ResponseEntity<List<ParkAddressDto>> getAllParkAddresses() {
        List<ParkAddressDto> parkAddresses = parkAddressService.findAll();
        return ResponseEntity.ok(parkAddresses);
    }

    @Operation(summary = "Update a Park Address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Park Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Park Address not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ParkAddressDto> updateParkAddress(@PathVariable Long id, @RequestBody ParkAddressDto parkAddressDto) {
        ParkAddressDto updatedParkAddress = parkAddressService.update(parkAddressDto);
        return ResponseEntity.ok(updatedParkAddress);
    }

    @Operation(summary = "Delete a Park Address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Park Address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Park Address not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkAddress(@PathVariable Long id) {
        parkAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

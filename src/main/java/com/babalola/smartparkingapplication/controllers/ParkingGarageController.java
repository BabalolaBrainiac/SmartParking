package com.babalola.smartparkingapplication.controllers;

import com.babalola.smartparkingapplication.constants.ApplicationUrlMapping;
import com.babalola.smartparkingapplication.dtos.ParkingGarageDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.services.ParkingGarageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ParkingGarageController.ParkingGarageBaseUrl)
public class ParkingGarageController {

    public static final String ParkingGarageBaseUrl = ApplicationUrlMapping.PARKING_GARAGE_API;

    private final ParkingGarageService parkingGarageService;

    @Autowired
    public ParkingGarageController(ParkingGarageService parkingGarageService) {
        this.parkingGarageService = parkingGarageService;
    }

    @Operation(summary = "Create a new Parking Garage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Parking Garage created successfully"),
            @ApiResponse(responseCode = "409", description = "Parking Garage already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createParkingGarage(@RequestBody ParkingGarageDto parkingGarageDto) {
        try {
            ParkingGarageDto savedParkingGarage = parkingGarageService.save(parkingGarageDto);
            return new ResponseEntity<>(savedParkingGarage, HttpStatus.CREATED);
        } catch (ResourceExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get a Parking Garage by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking Garage found"),
            @ApiResponse(responseCode = "404", description = "Parking Garage not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ParkingGarageDto> getParkingGarageById(@PathVariable Long id) {
        return parkingGarageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all Parking Garages")
    @ApiResponse(responseCode = "200", description = "List of Parking Garages")
    @GetMapping
    public ResponseEntity<List<ParkingGarageDto>> getAllParkingGarages() {
        List<ParkingGarageDto> parkingGarages = parkingGarageService.findAll();
        return ResponseEntity.ok(parkingGarages);
    }

    @Operation(summary = "Update a Parking Garage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking Garage updated successfully"),
            @ApiResponse(responseCode = "404", description = "Parking Garage not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ParkingGarageDto> updateParkingGarage(@PathVariable Long id, @RequestBody ParkingGarageDto parkingGarageDto) {
        ParkingGarageDto updatedParkingGarage = parkingGarageService.update(parkingGarageDto);
        return ResponseEntity.ok(updatedParkingGarage);
    }

    @Operation(summary = "Delete a Parking Garage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Parking Garage deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Parking Garage not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingGarage(@PathVariable Long id) {
        parkingGarageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

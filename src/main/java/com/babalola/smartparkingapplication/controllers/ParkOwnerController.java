package com.babalola.smartparkingapplication.controllers;

import com.babalola.smartparkingapplication.constants.ApplicationUrlMapping;
import com.babalola.smartparkingapplication.dtos.ParkOwnerDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.services.ParkOwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.babalola.smartparkingapplication.controllers.ParkOwnerController.ParkOwnerBaseUrl;

@RestController
@RequestMapping(ParkOwnerBaseUrl)
public class ParkOwnerController {
    public static final String ParkOwnerBaseUrl = ApplicationUrlMapping.PARK_OWNER_API;

    private final ParkOwnerService parkOwnerService;

    @Autowired
    public ParkOwnerController(ParkOwnerService parkOwnerService) {
        this.parkOwnerService = parkOwnerService;
    }

    @Operation(summary = "Create a new ParkOwner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ParkOwner created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "ParkOwner already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createParkOwner(@RequestBody ParkOwnerDto parkOwnerDto) {
        try {
            ParkOwnerDto savedParkOwner = parkOwnerService.save(parkOwnerDto);
            return new ResponseEntity<>(savedParkOwner, HttpStatus.CREATED);
        } catch (ResourceExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get a ParkOwner by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ParkOwner found"),
            @ApiResponse(responseCode = "404", description = "ParkOwner not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ParkOwnerDto> getParkOwnerById(@PathVariable Long id) {
        return parkOwnerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all ParkOwners")
    @ApiResponse(responseCode = "200", description = "List of ParkOwners")
    @GetMapping
    public ResponseEntity<List<ParkOwnerDto>> getAllParkOwners() {
        List<ParkOwnerDto> parkOwners = parkOwnerService.findAll();
        return ResponseEntity.ok(parkOwners);
    }

    @Operation(summary = "Update a ParkOwner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ParkOwner updated successfully"),
            @ApiResponse(responseCode = "404", description = "ParkOwner not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateParkOwner(@PathVariable Long id, @RequestBody ParkOwnerDto parkOwnerDto) {
        try {
            ParkOwnerDto updatedParkOwner = parkOwnerService.update(parkOwnerDto);
            return ResponseEntity.ok(updatedParkOwner);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a ParkOwner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ParkOwner deleted successfully"),
            @ApiResponse(responseCode = "404", description = "ParkOwner not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkOwner(@PathVariable Long id) {
        try {
            parkOwnerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.babalola.smartparkingapplication.controllers;

import com.babalola.smartparkingapplication.constants.ApplicationUrlMapping;
import com.babalola.smartparkingapplication.domain.entities.Location;
import com.babalola.smartparkingapplication.domain.mappers.LocationMapper;
import com.babalola.smartparkingapplication.dtos.LocationDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.services.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(LocationController.LocationBaseUrl)
public class LocationController {

    public static final String LocationBaseUrl = ApplicationUrlMapping.LOCATION_API;

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Operation(summary = "Create a new Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Location created successfully"),
            @ApiResponse(responseCode = "409", description = "Location already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody LocationDto locationDto) {
        try {


            Location savedLocation = locationService.save(locationDto);
            return new ResponseEntity<>(LocationMapper.INSTANCE.locationToLocationDTO(savedLocation), HttpStatus.CREATED);
        } catch (ResourceExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get a Location by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location found"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable Long id) {
        return locationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all Locations")
    @ApiResponse(responseCode = "200", description = "List of Locations")
    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        List<LocationDto> locations = locationService.findAll();
        return ResponseEntity.ok(locations);
    }

    @Operation(summary = "Update a Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location updated successfully"),
            @ApiResponse(responseCode = "404", description = "Location not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> updateLocation(@PathVariable Long id, @RequestBody LocationDto locationDto) {
        LocationDto updatedLocation = locationService.update(locationDto);
        return ResponseEntity.ok(updatedLocation);
    }

    @Operation(summary = "Delete a Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Location deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/proximity")
    public List<LocationDto> findWithinDistance(@RequestParam double longitude, @RequestParam double latitude, @RequestParam double distanceInMeters) {
        return locationService.findWithinDistance(longitude, latitude, distanceInMeters);
    }
}

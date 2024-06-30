package com.babalola.smartparkingapplication.controllers;

import com.babalola.smartparkingapplication.constants.ApplicationUrlMapping;
import com.babalola.smartparkingapplication.domain.enums.VehicleTypeEnum;
import com.babalola.smartparkingapplication.dtos.AvailableParkingSpaceDto;
import com.babalola.smartparkingapplication.services.AvailableParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AvailableParkingSpaceController.AvailableParkingSpaceUrl)
@RequiredArgsConstructor
public class AvailableParkingSpaceController {

    public static final String AvailableParkingSpaceUrl = ApplicationUrlMapping.PARKING_SPACE;
    private final AvailableParkingSpaceService availableParkingSpaceService;

    @PostMapping
    public ResponseEntity<AvailableParkingSpaceDto> create(@RequestBody AvailableParkingSpaceDto availableParkingSpaceDto) {
        AvailableParkingSpaceDto savedSpace = availableParkingSpaceService.save(availableParkingSpaceDto);
        return ResponseEntity.ok(savedSpace);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailableParkingSpaceDto> getById(@PathVariable Long id) {
        return availableParkingSpaceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AvailableParkingSpaceDto>> getAll() {
        List<AvailableParkingSpaceDto> spaces = availableParkingSpaceService.findAll();
        return ResponseEntity.ok(spaces);
    }

    @PutMapping
    public ResponseEntity<AvailableParkingSpaceDto> update(@RequestBody AvailableParkingSpaceDto availableParkingSpaceDto) {
        AvailableParkingSpaceDto updatedSpace = availableParkingSpaceService.update(availableParkingSpaceDto);
        return ResponseEntity.ok(updatedSpace);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        availableParkingSpaceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<AvailableParkingSpaceDto>> getByOwnerId(@PathVariable Long ownerId) {
        List<AvailableParkingSpaceDto> spaces = availableParkingSpaceService.findByOwnerId(ownerId);
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/garage/{garageId}")
    public ResponseEntity<List<AvailableParkingSpaceDto>> getByGarageId(@PathVariable Long garageId) {
        List<AvailableParkingSpaceDto> spaces = availableParkingSpaceService.findByGarageId(garageId);
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/vehicle-type/{vehicleType}")
    public ResponseEntity<List<AvailableParkingSpaceDto>> getByVehicleType(@PathVariable String vehicleType) {
        VehicleTypeEnum vehicleTypeEnum = VehicleTypeEnum.valueOf(vehicleType.toUpperCase());
        List<AvailableParkingSpaceDto> spaces = availableParkingSpaceService.findByVehicleType(vehicleTypeEnum);
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/location")
    public ResponseEntity<List<AvailableParkingSpaceDto>> getByLocation(@RequestParam double latitude, @RequestParam double longitude) {
        List<AvailableParkingSpaceDto> spaces = availableParkingSpaceService.findByLocation(latitude, longitude);
        return ResponseEntity.ok(spaces);
    }
}

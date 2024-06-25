package com.babalola.smartparkingapplication.dtos;

import java.util.List;

public record ParkingGarageDto(
        Long id,
        ParkAddressDto address,
        LocationDto location,
        List<VehicleDto> vehicles,
        List<AvailableParkingSpaceDto> availableParkingSpaces
) {}
package com.babalola.smartparkingapplication.dtos;

import java.util.List;

public record ParkingGarageResponseDto(
        Long id,
        Long addressId,
        String street,
        String city,
        String state,
        String zipCode,
        Long locationId,
        List<Long> vehicleIds,
        List<Long> availableParkingSpaceIds,
        Long parkOwnerId
) {}

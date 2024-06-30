package com.babalola.smartparkingapplication.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;

public record ParkingGarageDto(

        @Null
        Long id,

        @NotNull
        @NotBlank
        ParkAddressDto address,
        LocationDto location,
        List<VehicleDto> vehicles,
        List<AvailableParkingSpaceDto> availableParkingSpaces,
        ParkOwnerDto parkOwner
) {}

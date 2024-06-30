package com.babalola.smartparkingapplication.dtos;

import com.babalola.smartparkingapplication.domain.entities.ParkingGarage;
import com.babalola.smartparkingapplication.domain.enums.VehicleTypeEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record AvailableParkingSpaceDto(

        @Null
        Long id,

         @NotNull
         Integer availableSpaces,
        @NotNull
        VehicleTypeEnum vehicleType,
        @Null
        ParkingGarage parkingGarage
) {}
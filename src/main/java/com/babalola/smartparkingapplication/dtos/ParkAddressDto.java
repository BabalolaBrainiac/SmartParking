package com.babalola.smartparkingapplication.dtos;

import com.babalola.smartparkingapplication.domain.entities.Location;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Null;

public record ParkAddressDto(

        @Null
        Long id,
        String street,
        String city,
        String state,
        @Null
        String zipCode,
        Location location
) {}
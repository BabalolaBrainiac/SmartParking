package com.babalola.smartparkingapplication.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record LocationDto(

        @Null
        Long id,

        @NotNull
        @NotBlank
        double latitude,

        @NotNull
        @NotBlank
        double longitude,

        @Null
        String description
) {
}
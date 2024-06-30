package com.babalola.smartparkingapplication.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import java.util.List;

public record ParkOwnerDto (
        @Null
        Long id,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String email,

        @Nullable
        String phoneNumber,

        @Null
        Boolean isDeleted,

        @NotNull
        @NotBlank
        String userType,

        @Nullable
        List<ParkingGarageDto> parkingGarages
) { }

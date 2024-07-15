package com.babalola.smartparkingapplication.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import java.util.UUID;

public record UserDto(
        @Null Long id,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email,
        @Nullable String phoneNumber,
        @Null Boolean isDeleted,
        @NotBlank String userType,
        @Null UUID userId,
        @Nullable DriverDTO driver,
        @Nullable ParkOwnerDto parkOwner,
        @Nullable AdminUserDto adminUser
) { }

package com.babalola.smartparkingapplication.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.UUID;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

public record AdminUserDto(
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

        @Null
        UUID userId,

         @Nullable UserDto user,

        @Nullable
        String username,

        @Nullable
        String password
) { }

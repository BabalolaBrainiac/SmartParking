package com.babalola.smartparkingapplication.dtos;

public record DriverDTO (
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Boolean isDeleted,
        String userType
) {}
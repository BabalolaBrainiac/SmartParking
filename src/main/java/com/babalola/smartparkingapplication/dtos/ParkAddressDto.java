package com.babalola.smartparkingapplication.dtos;

public record ParkAddressDto(
        Long id,
        String street,
        String city,
        String state,
        String zipCode
) {}
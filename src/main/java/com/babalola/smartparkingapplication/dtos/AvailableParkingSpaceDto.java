package com.babalola.smartparkingapplication.dtos;

public record AvailableParkingSpaceDto(
        Long id,
        String spaceNumber,
        boolean isOccupied
) {}
package com.babalola.smartparkingapplication.dtos;

import com.babalola.smartparkingapplication.domain.enums.BookingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

public record BookingDto(
        @Null Long id,
        @NotNull Long availableParkingSpaceId,
        @NotNull Long userId,
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime,
        @Null BookingStatus status
) {
}

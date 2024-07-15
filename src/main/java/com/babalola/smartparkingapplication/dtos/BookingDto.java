package com.babalola.smartparkingapplication.dtos;

import com.babalola.smartparkingapplication.domain.enums.BookingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BookingDto(
        @NotNull Long id,
        @NotNull Long availableParkingSpaceId,
        @NotNull Long userId,
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime,
        @NotBlank BookingStatus status
) {
}

package com.babalola.smartparkingapplication.dtos;

import com.babalola.smartparkingapplication.domain.enums.TransactionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto(
        @NotNull Long id,
        @NotNull Long bookingId,
        @NotNull BigDecimal amount,
        @NotNull LocalDateTime transactionDate,
        @NotBlank String paymentProvider,
        @NotBlank TransactionStatus status
) {
}

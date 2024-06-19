package com.babalola.smartparkingapplication.dtos;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record vehicleDto(
        @NotBlank(message = "Make is mandatory")
        String make,

        @NotBlank(message = "Model is mandatory")
        String model,

        @Min(value = 1886, message = "Year should not be less than 1886")
        @Max(value = 2024, message = "Year should not be greater than the current year")
        Integer year,

        String color,

        @NotBlank(message = "VIN is mandatory")
        @Size(min = 17, max = 17, message = "VIN must be 17 characters")
        String vin,

        String licensePlate,

        @NotBlank(message = "Vehicle type is mandatory")
        String vehicleType,

        @Min(value = 0, message = "Mileage should be a positive number")
        Integer mileage,

        String fuelType,

        Boolean isElectric
) {
}

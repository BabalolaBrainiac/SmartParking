package com.babalola.smartparkingapplication.models;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AdminUserRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @Nullable
    private String phoneNumber;

    @NotNull
    @NotBlank
    private String userType;

}

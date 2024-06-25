package com.babalola.smartparkingapplication.auth.roles;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserRoles {
    ADMIN_USER("admin_user"),
    DRIVER("driver"),
    PARK_OWNER("park_owner");

    private final String role;
}
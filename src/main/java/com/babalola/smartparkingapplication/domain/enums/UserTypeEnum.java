package com.babalola.smartparkingapplication.domain.enums;

public enum UserTypeEnum {
    DRIVER("Driver"),
    PARK_OWNER("Park Owner"),
    ADMIN("Administrator");

    private final String description;

    UserTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
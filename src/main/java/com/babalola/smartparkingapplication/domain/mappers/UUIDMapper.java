package com.babalola.smartparkingapplication.domain.mappers;

import org.mapstruct.Named;

import java.util.UUID;

public interface UUIDMapper {
    @Named("uuidToString")
    static String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("stringToUuid")
    static UUID stringToUuid(String string) {
        return string != null ? UUID.fromString(string) : null;
    }
}

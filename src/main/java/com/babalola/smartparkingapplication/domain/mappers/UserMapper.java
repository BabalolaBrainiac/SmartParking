package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.entities.User;
import com.babalola.smartparkingapplication.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DriverMapper.class, ParkOwnerMapper.class, AdminMapper.class, UUIDMapper.class})
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Named("uuidToString")
    public static String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("stringToUuid")
    public static UUID stringToUuid(String string) {
        return string != null ? UUID.fromString(string) : null;
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "driver", target = "driver")
    @Mapping(source = "parkOwner", target = "parkOwner")
    @Mapping(source = "adminUser", target = "adminUser")
    public abstract UserDto userToUserDto(User user);

    @Mapping(source = "id", target = "id")
    public abstract User userDtoToUser(UserDto userDto);
}
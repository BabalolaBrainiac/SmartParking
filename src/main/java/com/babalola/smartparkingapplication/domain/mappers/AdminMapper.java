package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.entities.AdminUser;
import com.babalola.smartparkingapplication.dtos.AdminUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    @Named("uuidToString")
    static String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("stringToUuid")
    static UUID stringToUuid(String string) {
        return string != null ? UUID.fromString(string) : null;
    }

    @Mapping(source = "userType", target = "userType")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "uuidToString")
    AdminUserDto adminToAdminDTO(AdminUser admin);

    @Mapping(source = "userType", target = "userType")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "stringToUuid")
    AdminUser adminDTOToAdmin(AdminUserDto adminDTO);
}

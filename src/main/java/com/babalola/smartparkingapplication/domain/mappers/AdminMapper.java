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


    @Mapping(source = "user", target = "user")
    AdminUserDto adminToAdminDTO(AdminUser admin);

    @Mapping(source = "user", target = "user")
    AdminUser adminDTOToAdmin(AdminUserDto adminDTO);
}

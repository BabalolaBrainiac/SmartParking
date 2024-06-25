package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.entities.AdminUser;
import com.babalola.smartparkingapplication.dtos.AdminUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    @Mapping(source = "userType", target = "userType")
    AdminUserDto adminToAdminDTO(AdminUser admin);

    @Mapping(source = "userType", target = "userType")
    AdminUser adminDTOToAdmin(AdminUserDto adminDTO);
}

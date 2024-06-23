package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.model.AdminUser;
import com.babalola.smartparkingapplication.dtos.AdminUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper
public interface AdminMapper {

    AdminMapper var = Mappers.getMapper(AdminMapper.class);

    @Mapping(source = "userType", target = "userType")
    AdminUserDto adminToAdminDTO(AdminUser admin);

    @Mapping(source = "userTypeEnum", target = "userType")
    AdminUser adminDTOToAdmin(AdminUserDto adminDTO);
}

package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.entities.Driver;
import com.babalola.smartparkingapplication.dtos.DriverDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DriverMapper {
        DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

        @Mapping(source = "user", target = "user")
        DriverDTO driverToDriverDTO(Driver driver);

        @Mapping(source = "user", target = "user")
        Driver driverDtoToDriver(DriverDTO driverDto);
}

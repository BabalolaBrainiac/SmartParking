package com.babalola.smartparkingapplication.domain.mappers;


import com.babalola.smartparkingapplication.domain.model.Driver;
import com.babalola.smartparkingapplication.dtos.DriverDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DriverMapper {
        DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

        @Mapping(source = "userType", target = "userType")
        DriverDTO driverToDriverDTO(Driver driver);

        @Mapping(source = "userType", target = "userType")
        Driver driverDTOToDriver(DriverDTO driverDTO);
}

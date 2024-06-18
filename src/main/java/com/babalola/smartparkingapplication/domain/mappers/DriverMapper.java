package com.babalola.smartparkingapplication.domain.mappers;


@Mapper
public interface DriverToDriverDtoMapper {
        DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

        @Mapping(source = "userType", target = "userType")
        DriverDTO driverToDriverDTO(Driver driver);

        @Mapping(source = "userType", target = "userType")
        Driver driverDTOToDriver(DriverDTO driverDTO);
}

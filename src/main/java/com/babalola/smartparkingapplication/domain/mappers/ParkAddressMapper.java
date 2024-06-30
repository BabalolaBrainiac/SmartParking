package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.entities.ParkAddress;
import com.babalola.smartparkingapplication.dtos.ParkAddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParkAddressMapper {
    ParkAddressMapper INSTANCE = Mappers.getMapper(ParkAddressMapper.class);

    @Mapping(source = "id", target = "id")
    ParkAddressDto parkAddressToParkAddressDTO(ParkAddress parkAddress);

    @Mapping(source = "id", target = "id")
    ParkAddress parkAddressDTOToParkAddress(ParkAddressDto parkAddressDto);
}

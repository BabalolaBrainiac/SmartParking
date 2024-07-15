package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.entities.ParkOwner;
import com.babalola.smartparkingapplication.dtos.ParkOwnerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ParkOwnerMapper {
    ParkOwnerMapper INSTANCE = Mappers.getMapper(ParkOwnerMapper.class);

    @Mapping(source = "user", target = "user")
    ParkOwnerDto parkOwnerToParkOwnerDTO(ParkOwner parkOwner);

    @Mapping(source = "user", target = "user")
    ParkOwner parkOwnerDtoToParkOwner(ParkOwnerDto parkOwnerDto);
}

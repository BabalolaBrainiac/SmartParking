package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.entities.ParkOwner;
import com.babalola.smartparkingapplication.dtos.ParkOwnerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParkOwnerMapper {
    ParkOwnerMapper INSTANCE = Mappers.getMapper(ParkOwnerMapper.class);

    @Mapping(source = "userType", target = "userType")
    ParkOwnerDto parkOwnerToParkOwnerDTO(ParkOwner parkOwner);

    @Mapping(source = "userType", target = "userType")
    ParkOwner parkOwnerDTOToParkOwner(ParkOwnerDto parkOwnerDTO);
}
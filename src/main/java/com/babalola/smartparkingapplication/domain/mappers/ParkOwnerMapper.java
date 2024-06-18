package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.model.ParkOwner;
import com.babalola.smartparkingapplication.dtos.ParkOwnerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParkOwnerMapper {
    ParkOwnerMapper INSTANCE = Mappers.getMapper(ParkOwnerMapper.class);

    @Mapping(source = "userType", target = "userTypeEnum")
    ParkOwnerDto parkOwnerToParkOwnerDTO(ParkOwner parkOwner);

    @Mapping(source = "userTypeEnum", target = "userType")
    ParkOwner parkOwnerDTOToParkOwner(ParkOwnerDto parkOwnerDTO);
}
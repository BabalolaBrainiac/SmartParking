package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.entities.ParkingGarage;
import com.babalola.smartparkingapplication.dtos.ParkingGarageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParkingGarageMapper {
    ParkingGarageMapper INSTANCE = Mappers.getMapper(ParkingGarageMapper.class);

    @Mapping(source = "id", target = "id")
    ParkingGarageDto parkingGarageToParkingGarageDTO(ParkingGarage parkingGarage);

    @Mapping(source = "id", target = "id")
    ParkingGarage parkingGarageDTOToParkingGarage(ParkingGarageDto parkingGarageDto);
}

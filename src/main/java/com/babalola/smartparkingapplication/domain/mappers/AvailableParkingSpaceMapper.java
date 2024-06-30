package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.entities.AvailableParkingSpace;
import com.babalola.smartparkingapplication.dtos.AvailableParkingSpaceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AvailableParkingSpaceMapper {

    AvailableParkingSpaceMapper INSTANCE = Mappers.getMapper(AvailableParkingSpaceMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parkingGarage", ignore = true)
    AvailableParkingSpace availableParkingSpaceDtoToAvailableParkingSpace(AvailableParkingSpaceDto dto);

    List<AvailableParkingSpace> availableParkingSpaceDtosToAvailableParkingSpaces(List<AvailableParkingSpaceDto> dtos);
}

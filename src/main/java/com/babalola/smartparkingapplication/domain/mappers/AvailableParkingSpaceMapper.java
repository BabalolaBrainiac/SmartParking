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

    @Mapping(target = "parkingGarage", ignore = true)
    AvailableParkingSpaceDto toDto(AvailableParkingSpace availableParkingSpace);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parkingGarage", ignore = true)
    AvailableParkingSpace toEntity(AvailableParkingSpaceDto availableParkingSpaceDto);

    List<AvailableParkingSpaceDto> toDtos(List<AvailableParkingSpace> entities);
    List<AvailableParkingSpace> toEntities(List<AvailableParkingSpaceDto> dtos);
}

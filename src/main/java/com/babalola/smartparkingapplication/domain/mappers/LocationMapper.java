package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.entities.Location;
import com.babalola.smartparkingapplication.dtos.LocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(source = "id", target = "id")
    LocationDto locationToLocationDTO(Location location);

    @Mapping(source = "id", target = "id")
    Location locationDTOToLocation(LocationDto locationDto);
}

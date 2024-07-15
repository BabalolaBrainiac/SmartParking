package com.babalola.smartparkingapplication.domain.mappers;

import com.babalola.smartparkingapplication.domain.entities.ParkingGarage;
import com.babalola.smartparkingapplication.dtos.ParkingGarageDto;
import com.babalola.smartparkingapplication.dtos.ParkingGarageResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = UUIDMapper.class)
public interface ParkingGarageMapper {
    ParkingGarageMapper INSTANCE = Mappers.getMapper(ParkingGarageMapper.class);

    @Mapping(target = "address", source = "address")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "parkOwner", source = "parkOwner")
    ParkingGarageDto parkingGarageToParkingGarageDTO(ParkingGarage parkingGarage);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "parkOwner", source = "parkOwner")
    ParkingGarage parkingGarageDTOToParkingGarage(ParkingGarageDto parkingGarageDto);

    @Mapping(target = "addressId", source = "address.id")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "state", source = "address.state")
    @Mapping(target = "zipCode", source = "address.zipCode")
    @Mapping(target = "locationId", source = "address.location.id")
    @Mapping(target = "vehicleIds", expression = "java(parkingGarage.getVehicles().stream().map(com.babalola.smartparkingapplication.domain.entities.Vehicle::getId).collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "availableParkingSpaceIds", expression = "java(parkingGarage.getAvailableParkingSpaces().stream().map(com.babalola.smartparkingapplication.domain.entities.AvailableParkingSpace::getId).collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "parkOwnerId", source = "parkOwner.id")
    ParkingGarageResponseDto parkingGarageToParkingGarageResponseDto(ParkingGarage parkingGarage);
}

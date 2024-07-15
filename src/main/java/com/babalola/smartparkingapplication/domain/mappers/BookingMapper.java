package com.babalola.smartparkingapplication.domain.mappers;


import com.babalola.smartparkingapplication.domain.entities.Booking;
import com.babalola.smartparkingapplication.dtos.BookingDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(source = "availableParkingSpace.id", target = "availableParkingSpaceId")
    @Mapping(source = "user.id", target = "userId")
    BookingDto bookingToBookingDto(Booking booking);

    @Mapping(source = "availableParkingSpaceId", target = "availableParkingSpace.id")
    @Mapping(source = "userId", target = "user.id")
    Booking bookingDtoToBooking(BookingDto bookingDto);

    @Mapping(source = "availableParkingSpaceId", target = "availableParkingSpace.id")
    @Mapping(source = "userId", target = "user.id")
    void updateBookingFromDto(BookingDto bookingDto, @MappingTarget Booking booking);
}

package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.enums.BookingStatus;
import com.babalola.smartparkingapplication.dtos.BookingDto;

import java.util.List;

public interface BookingService {
    BookingDto createBooking(BookingDto bookingDto) throws Exception;
    BookingDto updateBooking(Long bookingId, BookingDto bookingDto);
    void cancelBooking(Long bookingId);
    BookingDto getBookingById(Long bookingId);
    List<BookingDto> getAllBookings();

    List<BookingDto> getBookingsByUserId(Long userId);
    List<BookingDto> getBookingByUserIdAndBookingStatus(Long userId, BookingStatus bookingStatus);

    List<BookingDto> getAllParkOwnersBooking(Long parkOwnerId);

    List<BookingDto> getBookingsByParkOwnerAndStatus(Long parkOwnerId, BookingStatus bookingStatus);



}

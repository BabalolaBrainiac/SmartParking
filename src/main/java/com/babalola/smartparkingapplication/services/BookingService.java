package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.dtos.BookingDto;

import java.util.List;

public interface BookingService {
    BookingDto createBooking(BookingDto bookingDto);
    BookingDto updateBooking(Long bookingId, BookingDto bookingDto);
    void cancelBooking(Long bookingId);
    BookingDto getBookingById(Long bookingId);
    List<BookingDto> getAllBookings();
}

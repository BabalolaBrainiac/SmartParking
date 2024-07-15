package com.babalola.smartparkingapplication.services;


import com.babalola.smartparkingapplication.domain.entities.Booking;
import com.babalola.smartparkingapplication.domain.enums.BookingStatus;
import com.babalola.smartparkingapplication.domain.mappers.BookingMapper;
import com.babalola.smartparkingapplication.dtos.BookingDto;
import com.babalola.smartparkingapplication.repositories.BookingRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        Booking booking = bookingMapper.bookingDtoToBooking(bookingDto);
        booking.setStatus(BookingStatus.PENDING);
        booking = bookingRepository.save(booking);
        return bookingMapper.bookingToBookingDto(booking);
    }

    @Override
    public BookingDto updateBooking(Long bookingId, BookingDto bookingDto) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        bookingMapper.updateBookingFromDto(bookingDto, booking);
        booking = bookingRepository.save(booking);
        return bookingMapper.bookingToBookingDto(booking);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    @Override
    public BookingDto getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return bookingMapper.bookingToBookingDto(booking);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::bookingToBookingDto)
                .collect(Collectors.toList());
    }
}

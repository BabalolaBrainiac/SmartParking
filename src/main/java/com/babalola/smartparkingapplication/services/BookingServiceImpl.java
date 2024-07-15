package com.babalola.smartparkingapplication.services;


import com.babalola.smartparkingapplication.domain.entities.AvailableParkingSpace;
import com.babalola.smartparkingapplication.domain.entities.Booking;
import com.babalola.smartparkingapplication.domain.entities.User;
import com.babalola.smartparkingapplication.domain.enums.BookingStatus;
import com.babalola.smartparkingapplication.domain.mappers.BookingMapper;
import com.babalola.smartparkingapplication.dtos.BookingDto;
import com.babalola.smartparkingapplication.repositories.AvailableParkingSpaceRepository;
import com.babalola.smartparkingapplication.repositories.BookingRepository;
import com.babalola.smartparkingapplication.repositories.UserRepository;
import com.babalola.smartparkingapplication.utils.LoggerService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {



    private LoggerService loggerService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AvailableParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    @Transactional
    public BookingDto createBooking(BookingDto bookingDto) {

        User user = userRepository.getById(bookingDto.userId());

        if(user.getId() == null) {
            throw new ResourceNotFoundException("User not found");
        }

        Optional<AvailableParkingSpace> optionalAvailableParkingSpace = parkingSpaceRepository.findById(bookingDto.availableParkingSpaceId());

        if (optionalAvailableParkingSpace.isEmpty()) {
            throw new ResourceNotFoundException("Available parking space not found");
        }

        AvailableParkingSpace availableParkingSpace = optionalAvailableParkingSpace.get();

        if (availableParkingSpace.getAvailableSpaces() <= 0) {
            throw new ResourceNotFoundException("No available parking spaces");
        }



        Booking booking = bookingMapper.bookingDtoToBooking(bookingDto);
        booking.setStatus(BookingStatus.PENDING);

        booking = bookingRepository.save(booking);


        //TODO
        //Implement payment and process before updating parking space and mark booking as completed

        availableParkingSpace.setAvailableSpaces(availableParkingSpace.getAvailableSpaces() - 1);

        parkingSpaceRepository.save(availableParkingSpace);
        return bookingMapper.bookingToBookingDto(booking);
    }

    @Override
    @Transactional
    public BookingDto updateBooking(Long bookingId, BookingDto bookingDto) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        bookingMapper.updateBookingFromDto(bookingDto, booking);
        booking = bookingRepository.save(booking);
        return bookingMapper.bookingToBookingDto(booking);
    }

    @Override
    @Transactional
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

    public boolean processPendingOrCancelledBooking(Long bookingId) {
        var bookingDto = getBookingById(bookingId);
        Booking booking = bookingMapper.bookingDtoToBooking(bookingDto);

        if(booking.getStatus() == BookingStatus.PENDING || booking.getStatus() == BookingStatus.CANCELLED) {
            //TODO
            //Payment services and reprocess booking
            booking.setStatus(BookingStatus.CONFIRMED);
            bookingRepository.save(booking);
            loggerService.info("Booking" + booking.getId() + "successfully reporocessed");
            return true;
        }

        loggerService.info("Booking" + booking.getId() + "has already been processed");
        return false;
    }
}

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

    private static void processBooking(AvailableParkingSpace availableParkingSpace) {
        //TODO
        //Implement payment and process before updating parking space and mark booking as completed

        availableParkingSpace.setAvailableSpaces(availableParkingSpace.getAvailableSpaces() - 1);

        //TODO
        //Send Notification to user and park owner??
    }

    @Override
    @Transactional
    public BookingDto createBooking(BookingDto bookingDto) throws Exception {

        AvailableParkingSpace availableParkingSpace = validateAvailableParkingSpace(bookingDto);

        User user = getUser(bookingDto.userId());

        Booking booking = bookingMapper.bookingDtoToBooking(bookingDto);
        booking.setUser(user);
        booking.setStatus(BookingStatus.PENDING);

        try {
            booking = bookingRepository.save(booking);

            if(booking.getId() != null) {
                processBooking(availableParkingSpace);
            }

            parkingSpaceRepository.save(availableParkingSpace);
            return bookingMapper.bookingToBookingDto(booking);

        } catch (Exception exception) {
            loggerService.error("An error occured when creating a booking", exception);
            return null;
        }
    }

    private AvailableParkingSpace validateAvailableParkingSpace(BookingDto bookingDto) throws ResourceNotFoundException {
        Optional<AvailableParkingSpace> optionalAvailableParkingSpace = Optional.ofNullable(parkingSpaceRepository.findById(bookingDto.availableParkingSpaceId()).orElseThrow(() -> new ResourceNotFoundException("Available parking space not found")));
        AvailableParkingSpace availableParkingSpace = optionalAvailableParkingSpace.get();

        if (availableParkingSpace.getAvailableSpaces() <= 0) {
            throw new ResourceNotFoundException("No available parking spaces");
        }
        return availableParkingSpace;
    }

    @Override
    @Transactional
    public BookingDto updateBooking(Long bookingId, BookingDto bookingDto) {
        Booking booking = getBooking(bookingId);
        bookingMapper.updateBookingFromDto(bookingDto, booking);
        booking = bookingRepository.save(booking);
        return bookingMapper.bookingToBookingDto(booking);
    }

    private Booking getBooking(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = getBooking(bookingId);
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    @Override
    public BookingDto getBookingById(Long bookingId) {
        Booking booking = getBooking(bookingId);
        return bookingMapper.bookingToBookingDto(booking);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::bookingToBookingDto)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getBookingsByUserId(Long userId) {
        User user = getUser(userId);
        return bookingRepository.findByUser(user).stream().map(bookingMapper::bookingToBookingDto).sorted().collect(Collectors.toList());
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<BookingDto> getBookingByUserIdAndBookingStatus(Long userId, BookingStatus bookingStatus) {
        User user = getUser(userId);
        return bookingRepository.findByUserAndBookingStatus(user.getId(), bookingStatus).stream().map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
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

    @Override
    public List<BookingDto> getAllParkOwnersBooking(Long parkOwnerId) {
        List<Booking> bookings = bookingRepository.findAllByParkOwnerId(parkOwnerId);
        return bookings.stream()
                .map(bookingMapper::bookingToBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getBookingsByParkOwnerAndStatus(Long parkOwnerId, BookingStatus bookingStatus) {
        List<Booking> bookings = bookingRepository.findAllByParkOwnerIdAndStatus(parkOwnerId, bookingStatus);
        return bookings.stream()
                .map(bookingMapper::bookingToBookingDto)
                .collect(Collectors.toList());
    }
}

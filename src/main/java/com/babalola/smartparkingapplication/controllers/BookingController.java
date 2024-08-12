package com.babalola.smartparkingapplication.controllers;


import com.babalola.smartparkingapplication.constants.ApplicationUrlMapping;
import com.babalola.smartparkingapplication.domain.enums.BookingStatus;
import com.babalola.smartparkingapplication.dtos.BookingDto;
import com.babalola.smartparkingapplication.services.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BookingController.BookingsUrl)
public class BookingController {
    public static final String BookingsUrl = ApplicationUrlMapping.BOOKINGS_API;

    @Autowired
    private BookingService bookingService;

    @Operation(summary = "Create a new booking")
    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) throws Exception {
        BookingDto createdBooking = bookingService.createBooking(bookingDto);
        return ResponseEntity.ok(createdBooking);
    }

    @Operation(summary = "Update a booking")
    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto) {
        BookingDto updatedBooking = bookingService.updateBooking(id, bookingDto);
        return ResponseEntity.ok(updatedBooking);
    }

    @Operation(summary = "Cancel a booking")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get a booking by ID")
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) {
        BookingDto booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @Operation(summary = "Get all bookings")
    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        List<BookingDto> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Get all park owners' booking")
    @GetMapping("/park-owner/{parkOwnerId}")
    public ResponseEntity<List<BookingDto>> getBookingsByParkOwner(@PathVariable Long parkOwnerId) {
        List<BookingDto> bookings = bookingService.getAllParkOwnersBooking(parkOwnerId);
        return ResponseEntity.ok(bookings);
    }


    @Operation(summary = "Get all park owners' booking and booking status")
    @GetMapping("/park-owner/{parkOwnerId}/status/{status}")
    public ResponseEntity<List<BookingDto>> getBookingsByParkOwnerAndStatus(@PathVariable Long parkOwnerId, @PathVariable BookingStatus status) {
        List<BookingDto> bookings = bookingService.getBookingsByParkOwnerAndStatus(parkOwnerId, status);
        return ResponseEntity.ok(bookings);
    }


    @Operation(summary = "Get all a users bookings")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDto>> getBookingByUserId(@PathVariable Long userId) {
        List<BookingDto> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }


    @Operation(summary = "Get all users booking by Id and Booking status")
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<BookingDto>> getBookingByUserIdAndBookingStatus(@PathVariable Long userId, @PathVariable BookingStatus status) {
        List<BookingDto> bookings = bookingService.getBookingByUserIdAndBookingStatus(userId, status);
        return ResponseEntity.ok(bookings);
    }
}

package com.babalola.smartparkingapplication.controllers;


import com.babalola.smartparkingapplication.constants.ApplicationUrlMapping;
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
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
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
}

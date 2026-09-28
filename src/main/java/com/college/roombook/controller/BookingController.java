package com.college.roombook.controller;

import com.college.roombook.entity.Booking;
import com.college.roombook.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return bookingService.updateBooking(id, booking);
    }

    @PutMapping("/{id}/cancel")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "Booking cancelled successfully";
    }

    @PutMapping("/{id}/check-in")
    public String checkIn(@PathVariable Long id) {
        bookingService.checkIn(id);
        return "Booking checked in successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "Booking deleted successfully";
    }
}

package com.college.roombook.service;

import com.college.roombook.entity.Booking;
import com.college.roombook.entity.Employee;
import com.college.roombook.entity.Room;
import com.college.roombook.exception.BookingNotFoundException;
import com.college.roombook.repository.BookingRepository;
import com.college.roombook.repository.EmployeeRepository;
import com.college.roombook.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final EmployeeRepository employeeRepository;

    public BookingService(BookingRepository bookingRepository,
                          RoomRepository roomRepository,
                          EmployeeRepository employeeRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.employeeRepository = employeeRepository;
    }

    public Booking createBooking(Booking booking) {
        if (booking.getRoomId() == null || booking.getEmployeeId() == null) {
            throw new RuntimeException("Room ID and Employee ID are required");
        }

        if (booking.getStartTime() == null || booking.getEndTime() == null) {
            throw new RuntimeException("Start time and end time are required");
        }

        if (!booking.getEndTime().isAfter(booking.getStartTime())) {
            throw new RuntimeException("End time must be after start time");
        }

        if (booking.getStartTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Start time cannot be in the past");
        }

        Room room = roomRepository.findById(booking.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + booking.getRoomId()));

        Employee employee = employeeRepository.findById(booking.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + booking.getEmployeeId()));

        if (!room.isActive()) {
            throw new RuntimeException("Room is not active");
        }

        if (booking.getAttendees() == null || booking.getAttendees() <= 0) {
            throw new RuntimeException("Number of attendees must be greater than 0");
        }

        if (booking.getAttendees() > room.getCapacity()) {
            throw new RuntimeException("Number of attendees exceeds room capacity");
        }

        List<Booking> conflicts =
                bookingRepository.findByRoomIdAndStatusAndStartTimeLessThanAndEndTimeGreaterThan(
                        booking.getRoomId(),
                        "CONFIRMED",
                        booking.getEndTime(),
                        booking.getStartTime());

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Room is already booked for the selected time range");
        }

        booking.setStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    public Booking updateBooking(Long id, Booking booking) {
        Booking existingBooking = getBookingById(id);

        if (!"CONFIRMED".equals(existingBooking.getStatus())) {
            throw new RuntimeException("Only confirmed bookings can be updated");
        }

        existingBooking.setRoomId(booking.getRoomId());
        existingBooking.setEmployeeId(booking.getEmployeeId());
        existingBooking.setStartTime(booking.getStartTime());
        existingBooking.setEndTime(booking.getEndTime());
        existingBooking.setAttendees(booking.getAttendees());

        return createUpdatedBooking(existingBooking);
    }

    private Booking createUpdatedBooking(Booking booking) {
        Room room = roomRepository.findById(booking.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + booking.getRoomId()));

        if (!booking.getEndTime().isAfter(booking.getStartTime())) {
            throw new RuntimeException("End time must be after start time");
        }

        if (booking.getAttendees() == null || booking.getAttendees() <= 0) {
            throw new RuntimeException("Number of attendees must be greater than 0");
        }

        if (booking.getAttendees() > room.getCapacity()) {
            throw new RuntimeException("Number of attendees exceeds room capacity");
        }

        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
    }

    public void checkIn(Long id) {
        Booking booking = getBookingById(id);
        if (!"CONFIRMED".equals(booking.getStatus())) {
            throw new RuntimeException("Only confirmed bookings can be checked in");
        }
        booking.setStatus("CHECKED_IN");
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        Booking booking = getBookingById(id);
        bookingRepository.delete(booking);
    }
}

package com.college.roombook.repository;

import com.college.roombook.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoomIdAndStatusAndStartTimeLessThanAndEndTimeGreaterThan(
            Long roomId, String status, LocalDateTime endTime, LocalDateTime startTime);
}

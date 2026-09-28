package com.college.roombook.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomId;
    private Long employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer attendees;
    private String status;

    public Booking() {
    }

    public Booking(Long id, Long roomId, Long employeeId, LocalDateTime startTime,
                    LocalDateTime endTime, Integer attendees, String status) {
        this.id = id;
        this.roomId = roomId;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attendees = attendees;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Integer getAttendees() { return attendees; }
    public void setAttendees(Integer attendees) { this.attendees = attendees; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

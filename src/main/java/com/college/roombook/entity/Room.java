package com.college.roombook.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer capacity;
    private boolean projector;
    private boolean whiteboard;
    private boolean active;

    public Room() {
    }

    public Room(Long id, String name, Integer capacity, boolean projector, boolean whiteboard, boolean active) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.projector = projector;
        this.whiteboard = whiteboard;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public boolean isProjector() { return projector; }
    public void setProjector(boolean projector) { this.projector = projector; }

    public boolean isWhiteboard() { return whiteboard; }
    public void setWhiteboard(boolean whiteboard) { this.whiteboard = whiteboard; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

package com.college.roombook.service;

import com.college.roombook.entity.Room;
import com.college.roombook.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    public Room updateRoom(Long id, Room room) {
        Room existingRoom = getRoomById(id);
        existingRoom.setName(room.getName());
        existingRoom.setCapacity(room.getCapacity());
        existingRoom.setProjector(room.isProjector());
        existingRoom.setWhiteboard(room.isWhiteboard());
        existingRoom.setActive(room.isActive());
        return roomRepository.save(existingRoom);
    }

    public void deleteRoom(Long id) {
        Room room = getRoomById(id);
        roomRepository.delete(room);
    }
}

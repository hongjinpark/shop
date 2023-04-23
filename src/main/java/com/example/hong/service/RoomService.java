package com.example.hong.service;


import com.example.hong.entity.Room;
import com.example.hong.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    List<Room> roomList = new ArrayList<>();
    static int roomNumber = 0;
    public String createRoom(Map<Object, Object> params) {

        String roomName = (String) params.get("roomName");
        /*Room room = roomRepository.findOne(roomName)*/
        return null;
    }
    public Room getRoom(int id) {
        return roomRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void insertRoom(Room room) {
        roomRepository.save(room);
    }
    public Room update(int id, Room room) {
        Room room1 = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
        room1.update(room.getRoomName(), room.getRoomNumber(), room.getRoomPassword());
        return room1;
    }

    public void delete(int id) {
        roomRepository.deleteById(id);
    }

    public String joinRoom(Map<Object, Object> params) {
        String roomName = (String) params.get("roomName");
        String roomPassword = (String) params.get("roomPassword");
        return null;
    }

    public String exitRoom(Map<Object, Object> params) {
        String roomName = (String) params.get("roomName");
        return null;
    }
}

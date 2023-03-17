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
}

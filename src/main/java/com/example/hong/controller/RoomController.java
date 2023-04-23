package com.example.hong.controller;

import com.example.hong.entity.Room;
import com.example.hong.mapper.RoomMapper;
import com.example.hong.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;
    @GetMapping
    public ResponseEntity getAllRoom() {
        Map<String, Object> result = roomMapper.getAllRoom();
        return ResponseEntity.ok(result);
    }

    // room 조회
    @GetMapping("/{id}")
    public Room getRoom(@PathVariable int id) {
        return roomService.getRoom(id);
    }

    // room insert
    @PostMapping
    public void insertRoom(@RequestBody Room room) {
        roomService.insertRoom(room);
    }
    // room update
    @PutMapping("/{id}")
    public Room update(@PathVariable int id , @RequestBody Room room) {
        return roomService.update(id, room);
    }
    // room delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        roomService.delete(id);
    }
    // room create
    @PostMapping("/create")
    public String createRoom(@RequestBody Map<Object, Object> params) {
        return roomService.createRoom(params);
    }
    // rooom join
    @PostMapping("/join")
    public String joinRoom(@RequestBody Map<Object, Object> params) {
        return roomService.joinRoom(params);
    }
    // room exit
    @PostMapping("/exit")
    public String exitRoom(@RequestBody Map<Object, Object> params) {
        return roomService.exitRoom(params);
    }
}

package com.example.hong.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int id;

    private String roomName;

    @Builder
    public Room(String roomName) {
        this.roomName = roomName;
    }

    public void setRoom(String roomName) {
        this.roomName = roomName;
    }
}

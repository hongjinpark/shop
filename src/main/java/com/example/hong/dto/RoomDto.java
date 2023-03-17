package com.example.hong.dto;


import com.example.hong.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    @NotEmpty(message = "방 이름을 입력 해주세요")
    private String roomName;

    public Room toEntity(){
        return Room.builder()
                .roomName(roomName)
                .build();
    }
}

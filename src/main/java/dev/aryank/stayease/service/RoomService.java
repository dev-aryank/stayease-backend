package dev.aryank.stayease.service;

import dev.aryank.stayease.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto createNewRoom(Long hotelId,RoomDto roomDto);
    List<RoomDto> getRoomsByHotel(Long hotelId);
    RoomDto getRoomById(Long hotelId, Long roomId);
    void deleteRoomById(Long roomId);

    RoomDto updateRoomById(Long hotelId, Long roomId, RoomDto roomDto);
}

package dev.aryank.stayease.service;

import dev.aryank.stayease.dto.HotelDto;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
}

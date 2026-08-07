package dev.aryank.stayease.service;

import dev.aryank.stayease.dto.HotelPriceDto;
import dev.aryank.stayease.dto.HotelSearchRequest;
import dev.aryank.stayease.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);
    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}

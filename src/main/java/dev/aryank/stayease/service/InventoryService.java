package dev.aryank.stayease.service;

import dev.aryank.stayease.dto.HotelPriceDto;
import dev.aryank.stayease.dto.HotelSearchRequest;
import dev.aryank.stayease.dto.InventoryDto;
import dev.aryank.stayease.dto.UpdateInventoryRequestDto;
import dev.aryank.stayease.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {

    void initializeRoomForAYear(Room room);
    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);

    List<InventoryDto> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto);
}

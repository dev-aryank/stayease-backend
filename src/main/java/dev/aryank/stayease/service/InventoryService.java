package dev.aryank.stayease.service;

import dev.aryank.stayease.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);
    void deleteFutureInventories(Room room);

}

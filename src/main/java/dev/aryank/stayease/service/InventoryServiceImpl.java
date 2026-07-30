package dev.aryank.stayease.service;

import dev.aryank.stayease.entity.Room;
import dev.aryank.stayease.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public void initializeRoomForAYear(Room roomId) {

    }
}

package dev.aryank.stayease.service;

import dev.aryank.stayease.dto.RoomDto;
import dev.aryank.stayease.entity.Hotel;
import dev.aryank.stayease.entity.Room;
import dev.aryank.stayease.exception.ResourceNotFoundException;
import dev.aryank.stayease.repository.HotelRepository;
import dev.aryank.stayease.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final InventoryService  inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating room in hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);
        if(hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getRoomsByHotel(Long hotelId) {
        log.info("Getting all rooms in hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long hotelId, Long roomId) {
        log.info("Getting room with ID: {}", roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: "+roomId));
        return modelMapper.map(room, RoomDto.class);
    }

    @Transactional
    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room with ID: {}", roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: "+roomId));
        inventoryService.deleteFutureInventories(room);
        roomRepository.deleteById(roomId);
    }
    /*
     * We cannot physically delete a Room after deleting only future inventories.
     *
     * Reason:
     * Past (and today's) inventory records still contain a foreign key (room_id)
     * pointing to this Room. PostgreSQL prevents deleting the Room because it
     * would leave orphaned references in the Inventory table, violating the
     * foreign key constraint.
     *
     * Possible solutions:
     * 1. Delete all inventory records associated with the room before deleting it.
     * 2. Soft delete the room (mark it inactive) and keep historical inventory.
     *    This is the preferred approach in real-world hotel booking systems since
     *    past inventory and bookings are valuable historical data.
     */
}

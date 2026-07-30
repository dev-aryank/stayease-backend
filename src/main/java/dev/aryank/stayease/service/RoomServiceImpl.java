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

//        todo: create inventory as soon as room is created and hotel is active
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
        boolean hotelExists = hotelRepository.existsById(hotelId);
        if(!hotelExists){
            throw new ResourceNotFoundException("Hotel not found with ID: "+hotelId);
        }
        log.info("Getting room with ID: {}", roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: "+roomId));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room with ID: {}", roomId);
        boolean exists = roomRepository.existsById(roomId);
        if (!exists) {throw new ResourceNotFoundException("Room not found with ID: "+roomId);}
        roomRepository.deleteById(roomId);
//        todo: delete all future inventory for this room
    }
}

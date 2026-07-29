package dev.aryank.stayease.service;

import dev.aryank.stayease.dto.HotelDto;
import dev.aryank.stayease.entity.Hotel;
import dev.aryank.stayease.exception.ResourceNotFoundException;
import dev.aryank.stayease.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new Hotel with name {}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created a new Hotel with ID {}", hotelDto.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting the Hotel with ID {}", id);
        Hotel hotel = hotelRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+id));
        return modelMapper.map(hotel, HotelDto.class);
    }
}

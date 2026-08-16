package dev.aryank.stayease.service;

import dev.aryank.stayease.dto.GuestDto;
import dev.aryank.stayease.entity.Booking;
import dev.aryank.stayease.entity.Guest;
import dev.aryank.stayease.entity.User;
import dev.aryank.stayease.repository.BookingRepository;
import dev.aryank.stayease.repository.GuestRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static dev.aryank.stayease.util.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestServiceImpl implements GuestService {
    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GuestDto> getAllGuests() {
        User user = getCurrentUser();
        log.info("Fetching all guests for user {}", user.getId());
        List<Guest> guests = guestRepository.findByUser(user);
        return guests.stream()
                .map((element) -> modelMapper.map(element, GuestDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GuestDto addNewGuest(GuestDto guestDto) {
        log.info("Adding new guest {}", guestDto);
        User user = getCurrentUser();
        Guest guest = modelMapper.map(guestDto, Guest.class);
        guest.setUser(user);
        Guest savedGuest = guestRepository.save(guest);
        log.info("Guest added with ID: {}", savedGuest.getId());
        return modelMapper.map(savedGuest, GuestDto.class);
    }

    @Override
    public void updateGuest(Long guestId, GuestDto guestDto) {
        log.info("Updating guest with ID: {}", guestId);
        Guest guest = guestRepository.findById(guestId).orElseThrow(
                () -> new EntityNotFoundException("Guest not found"));
        User user = getCurrentUser();
        if (!user.equals(guest.getUser())) throw new AccessDeniedException("This guest doesnt belong to you.");
        modelMapper.map(guestDto, guest);
        guest.setUser(user);
        guest.setId(guestId);
        guestRepository.save(guest);
        log.info("Updated guest with ID: {}", guestId);
    }

    @Override
    public void deleteGuest(Long guestId) {
        log.info("Deleting guest with ID: {}", guestId);
        Guest guest = guestRepository.findById(guestId).orElseThrow(
                () -> new EntityNotFoundException("Guest not found"));
        User user = getCurrentUser();
        if (!user.equals(guest.getUser())) throw new AccessDeniedException("This guest doesnt belong to you.");
        List<Booking> bookings = bookingRepository.findByGuestsContaining(guest);

        for (Booking booking : bookings) {
            booking.getGuests().remove(guest);
        }

        bookingRepository.saveAll(bookings);
        guestRepository.delete(guest);
        log.info("Deleted guest with ID: {}", guestId);
    }
}

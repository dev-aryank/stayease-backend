package dev.aryank.stayease.service;

import dev.aryank.stayease.dto.BookingDto;
import dev.aryank.stayease.dto.BookingRequest;
import dev.aryank.stayease.dto.GuestDto;

import java.util.List;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}

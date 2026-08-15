package dev.aryank.stayease.service;

import com.stripe.model.Event;
import dev.aryank.stayease.dto.BookingDto;
import dev.aryank.stayease.dto.BookingRequest;
import dev.aryank.stayease.dto.GuestDto;

import java.util.List;
import java.util.Map;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    String getBookingStatus(Long bookingId);
}

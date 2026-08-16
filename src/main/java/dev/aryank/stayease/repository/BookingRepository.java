package dev.aryank.stayease.repository;


import dev.aryank.stayease.dto.BookingDto;
import dev.aryank.stayease.entity.Booking;
import dev.aryank.stayease.entity.Guest;
import dev.aryank.stayease.entity.Hotel;
import dev.aryank.stayease.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByPaymentSessionId(String sessionId);

    List<Booking> findByHotel(Hotel hotel);

    List<Booking> findByHotelAndCreatedAtBetween(Hotel hotel, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Booking> findByUser(User user);

    List<Booking> findByGuestsContaining(Guest guest);
}

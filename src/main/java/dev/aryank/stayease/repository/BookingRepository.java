package dev.aryank.stayease.repository;


import dev.aryank.stayease.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

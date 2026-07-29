package dev.aryank.stayease.repository;

import dev.aryank.stayease.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface HotelRepository extends JpaRepository<Hotel, Long> {
}

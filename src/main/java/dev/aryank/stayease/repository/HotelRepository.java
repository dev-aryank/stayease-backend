package dev.aryank.stayease.repository;

import dev.aryank.stayease.entity.Hotel;
import dev.aryank.stayease.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByOwner(User user);
}

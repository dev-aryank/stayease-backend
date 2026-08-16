package dev.aryank.stayease.repository;

import dev.aryank.stayease.entity.Guest;
import dev.aryank.stayease.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByUser(User user);
}
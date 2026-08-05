package dev.aryank.stayease.repository;

import dev.aryank.stayease.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}